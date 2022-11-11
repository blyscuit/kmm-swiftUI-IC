package co.nimblehq.blisskmmic

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}