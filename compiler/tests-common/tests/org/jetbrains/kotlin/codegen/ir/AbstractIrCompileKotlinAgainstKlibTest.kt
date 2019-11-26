/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.ir

import org.jetbrains.kotlin.codegen.AbstractBlackBoxCodegenTest
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.JVMConfigurationKeys
import org.jetbrains.kotlin.test.TargetBackend

import java.io.File

abstract class AbstractCompileKotlinAgainstKlibTest : AbstractBlackBoxCodegenTest() {
    lateinit var kLibName: String

    override fun getBackend() = TargetBackend.JVM_IR

    override fun doMultiFileTest(wholeFile: File, files: List<TestFile>) {
        kLibName = wholeFile.toString().removeSuffix(".kt") + ".klib"
        super.doMultiFileTest(wholeFile, files);
    }

    override fun updateConfiguration(configuration: CompilerConfiguration) {
        configuration.put(JVMConfigurationKeys.KLIB_PATHS, listOf(kLibName));
    }
}
