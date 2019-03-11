package ru.surf.networksample

fun runOnNetworkIO(block: () -> Unit) {

    AppExecutors.instance.networkIO.execute {
        block.invoke()
    }
}

fun runOnUi(block: () -> Unit) {
    AppExecutors.instance.mainThread.execute {
        block.invoke()
    }
}