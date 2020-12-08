/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.heb.continuedlearning.customlint

import com.android.SdkConstants
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass

/**
 * Sample code detector for lab.
 * Will report an issue if a class implements Activity
 */
class SampleCodeDetector : Detector(), SourceCodeScanner {

    // indicate what code is applicable
    override fun applicableSuperClasses(): List<String>? {
        return listOf(SdkConstants.CLASS_ACTIVITY)
    }

    // callback methods for applicable code
    override fun visitClass(context: JavaContext, declaration: UClass) {
        context.report(ISSUE, context.getNameLocation(declaration), "Instead of Activity, use AppCompatActivity instead.")
    }

    companion object {
        /** Issue describing the problem and pointing to the detector implementation */
        @JvmField
        val ISSUE: Issue = Issue.create(
            // ID: used in @SuppressLint warnings etc
            id = "ActivityUsage",
            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            briefDescription = "Reports an issue when Activity in extended",
            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            explanation = """
                    This check highlights classes that implement Activity. \
                    Blah blah blah.

                    Another paragraph here.
                    """, // no need to .trimIndent(), lint does that automatically
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.WARNING,
            implementation = Implementation(
                SampleCodeDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}
