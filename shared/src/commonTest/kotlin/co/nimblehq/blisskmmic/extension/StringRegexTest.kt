package co.nimblehq.blisskmmic.extension

import co.nimblehq.blisskmmic.Greeting
import co.nimblehq.blisskmmic.helpers.extensions.isValidEmail
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.test.assertTrue

class StringRegexTest {

    @Test
    fun `When calling isValidEmail emails- it returns correct validity`() {
        "a@a.co.uk".isValidEmail() shouldBe true
        "ab.cd@abc.org".isValidEmail() shouldBe true
        "a@a.com".isValidEmail() shouldBe true
        "abc@abc.co.uk".isValidEmail() shouldBe true
        "a@abcd.org".isValidEmail() shouldBe true
        "abcd.org".isValidEmail() shouldBe false
        "abcd@org".isValidEmail() shouldBe false
        "ab.cd@.org".isValidEmail() shouldBe false
    }
}
