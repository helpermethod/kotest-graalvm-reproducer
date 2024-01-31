package io.github.helpermethod.kotest.graalvm

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldEndWith

class ReproducerTests : FunSpec({
    test("should fail during nativeTest and nativeCompile") {
        "Hello GraalVM!" shouldEndWith "GraalVM!"
    }
})
