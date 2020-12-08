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

import com.android.tools.lint.detector.api.*

/**
 * Sample code detector for lab.
 * Write a SourceCodeScanner that will report a warning if
 */
class LabCodeDetector : Detector(), SourceCodeScanner {

    // TODO indicate what code this detector is interested in

    // TODO implement callback methods and write logic

    companion object {
        /** TODO create Issue describing the problem and pointing to the detector implementation */
        @JvmField
        val ISSUE: Issue = Issue.create()
    }
}
