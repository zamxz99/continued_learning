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
class SampleCodeDetectorTest : LintDetectorTest() {
    val activityPath = "src/com/test/TestActivity.kt"
    val activityFile = kotlin(activityPath,
        """
            package com.test
            
            import android.app.Activity
            class TestActivity() : Activity {}
            
        """.trimIndent())


    val activityPath1 = "src/com/test/SecondTestActivity.kt"
    val activityFile1 = kotlin(activityPath1,
        """
            package com.test
            class SecondTestActivity() : TestActivity(){}
        """.trimIndent())


    fun testBasic() {
        lint().files(activityFile, activityFile1)
                .run()
            .expectWarningCount(2)
            .expect("""
                    src/com/test/SecondTestActivity.kt:2: Warning: Instead of Activity, use AppCompatActivity instead. [ActivityUsage]
class SecondTestActivity() : TestActivity(){}
      ~~~~~~~~~~~~~~~~~~
src/com/test/TestActivity.kt:4: Warning: Instead of Activity, use AppCompatActivity instead. [ActivityUsage]
class TestActivity() : Activity {}
      ~~~~~~~~~~~~
0 errors, 2 warnings
                    """
                )
    }

    override fun getDetector(): Detector {
        return SampleCodeDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(SampleCodeDetector.ISSUE)
    }
}