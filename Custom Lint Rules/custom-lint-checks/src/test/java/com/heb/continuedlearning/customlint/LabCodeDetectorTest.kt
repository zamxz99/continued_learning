/*
 * Copyright (C) 2017 The Android Open Source Project
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

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class LabCodeDetectorTest : LintDetectorTest() {

    val dummyVM = kotlin("""
            package androidx.lifecycle
            class ViewModel(){}
            """)

    val testViewModel = kotlin("""
        package com.test
        import androidx.lifecycle.ViewModel
        class TestViewModel() : ViewModel {}
        """)

    val testFragment = kotlin("""
            package com.test
            import android.app.Fragment
            import androidx.lifecycle.ViewModel
            class TestFragment() : Fragment(){
                val a: String = "a"
                val vm: ViewModel? = null
                val b: String = "b"
                val vm: TestViewModel? = null
            }
        """)

    val testFragment2 = kotlin("""
        package com.test
        class TestFragment2() : TestFragment(){}
    """)

    val testFragment3 = kotlin(
        """
            package com.test
            import android.app.Fragment
            import androidx.lifecycle.ViewModel
            class TestFragment3() : Fragment(){
                val a: String = "a"
                val b: String = "b"
                //no viewmodel
            }
        """)

    val testActivity = kotlin(
        """
            package com.test
            import android.app.Activity
            import android.app.Fragment
            class TestActivity() : Activity(){
                val a: String = "a"
                val b: String = "b"
                //no viewmodel
                
                class TestFragmentInActivity(): Fragment(){}
            }
        """)


    fun testBasic() {
        lint().files(dummyVM, testViewModel, testFragment, testFragment2, testFragment3, testActivity)
                .run()
            .expectWarningCount(2)
    }

    override fun getDetector(): Detector {
        return LabCodeDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(LabCodeDetector.ISSUE)
    }
}