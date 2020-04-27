/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package core.openbsd

import org.lwjgl.generator.*

const val OPENBSD_PACKAGE = "org.lwjgl.system.openbsd"

fun config() {
    packageInfo(
        Module.CORE_OPENBSD,
        "Contains bindings to native APIs specific to the OpenBSD operating system."
    )
}

val long = IntegerType("long", PrimitiveMapping.POINTER)
val unsigned_long = IntegerType("unsigned long", PrimitiveMapping.POINTER, unsigned = true)
val unsigned_long_p = unsigned_long.p
