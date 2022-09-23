package com.joel.pagination.model.demo

class DefaultPaginator<Key, Items>(
    private val initialKey : Key,
    private val onLoadUpdated : (Boolean) -> Unit,
    private val onNextRequest : suspend (nextKey : Key) -> Result<List<Items>>,
    private val getNextKey : suspend (List<Items>) -> Key,
    private val onSuccess : suspend (items : List<Items>, newKey : Key) -> Unit,
    private val onError : suspend (Throwable?) -> Unit
) : Paginator<Key, Items> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadItems() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val results = onNextRequest(currentKey)
        isMakingRequest = false
        val items = results.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}