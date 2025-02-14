package com.sarajuhosova.graffe.intellij

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object GRaffeFileType : LanguageFileType(GRaffeLanguage) {
    override fun getName(): String = "GRaffe Graph Definition"

    override fun getDescription(): String =
        "A GRaffe language file for defining a graph"

    override fun getDefaultExtension(): String = "grf"

    override fun getIcon(): Icon = GRaffeIcons.ICON
}
