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
import com.android.tools.lint.detector.api.Severity

@Suppress("UnstableApiUsage")
class LabXMLDetectorTest : LintDetectorTest() {
    val layoutPath = "res/layout/activity_main.xml"
    val layoutXML = xml(layoutPath,
        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<androidx.constraintlayout.widget.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n" +
            "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
            "    android:layout_width=\"match_parent\"\n" +
            "    android:layout_height=\"match_parent\"\n" +
            "    tools:context=\".MainActivity\">\n" +
            "\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Hello World!\"\n" +
            "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
            "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
            "        app:layout_constraintTop_toTopOf=\"parent\" \n" +
            "        android:textSize=\"24sp\"/>\n" +
            "    <TextView\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Another TextView!\"\n" +
            "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
            "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
            "        app:layout_constraintTop_toTopOf=\"parent\" \n" +
            "        android:lineSpacingExtra=\"24sp\"/>\n" +
            "    <Button\n" +
            "        android:layout_width=\"wrap_content\"\n" +
            "        android:layout_height=\"wrap_content\"\n" +
            "        android:text=\"Hello World!\"\n" +
            "        app:layout_constraintBottom_toBottomOf=\"parent\"\n" +
            "        app:layout_constraintLeft_toLeftOf=\"parent\"\n" +
            "        app:layout_constraintRight_toRightOf=\"parent\"\n" +
            "        app:layout_constraintTop_toTopOf=\"parent\" \n" +
            "        android:paddingRight=\"24sp\"/>\n" +
            "\n" +
            "</androidx.constraintlayout.widget.ConstraintLayout>")

    val styleXMLFile = "res/values/styles.xml"
    val styleXML = xml(styleXMLFile,"<resources>\n" +
            "    <style name=\"TestStyle\">\n" +
            "        <item name=\"android:textSize\">32sp</item>\n" +
            "        <item name=\"android:lineSpacingExtra\">2sp</item>\n" +
            "    </style>\n" +
            "</resources>")

    fun testLayoutFile() {
        lint().files(layoutXML)
                .run()
            .expectCount(3, Severity.INFORMATIONAL)
    }

    fun testAllFiles() {
        lint().files(layoutXML, styleXML)
            .run()
            .expectCount(5, Severity.INFORMATIONAL)
    }

    override fun getDetector(): Detector {
        return LabXmlDetector()
    }

    override fun getIssues(): List<Issue> {
        return listOf(LabXmlDetector.ISSUE)
    }
}