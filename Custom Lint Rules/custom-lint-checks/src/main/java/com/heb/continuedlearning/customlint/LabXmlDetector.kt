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

import com.android.resources.ResourceType
import com.android.tools.lint.detector.api.*
import com.android.utils.text
import org.w3c.dom.Attr
import org.w3c.dom.Element

/**
 * XML for lab - find places in XML files where font sizes are being used directly (not via a dimen reference)
 */
class LabXmlDetector : ResourceXmlDetector() {

    private val spRegex = Regex("[0-9]*sp")

    override fun getApplicableAttributes(): Collection<String>? {
        return ALL
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        if (attribute.value.matches(spRegex)){
            reportDimenIssue(context, context.getLocation(attribute))
        }
    }

    override fun getApplicableElements(): Collection<String>? {
        return ALL
    }

    override fun visitElement(context: XmlContext, element: Element) {
        if (element.tagName == ResourceType.STYLE_ITEM.getName()
            && element.text().matches(spRegex)){
            reportDimenIssue(context, context.getLocation(element) )
        }
    }

    private fun reportDimenIssue(context: XmlContext, location: Location){
        context.report(ISSUE, location, "Failed to use @dimen/ value")
    }

    companion object {
        /**  TODO Create Issue describing the problem and pointing to the detector implementation */
        @JvmField
        val ISSUE: Issue = Issue.create(
                // ID: used in @SuppressLint warnings etc
                id = "InvalidFontSize",
                // Title -- shown in the IDE's preference dialog, as category headers in the
                // Analysis results window, etc
                briefDescription = "Usage of undeclared font size",
                // Full explanation of the issue; you can use some markdown markup such as
                // `monospace`, *italic*, and **bold**.
                explanation = """
                    Please use one of the font sizes declared in dimen.xml
                    """,
                category = Category.CORRECTNESS,
                priority = 5,
                severity = Severity.INFORMATIONAL,
                implementation = Implementation(
                        LabXmlDetector::class.java,
                        Scope.RESOURCE_FILE_SCOPE))
    }
}
