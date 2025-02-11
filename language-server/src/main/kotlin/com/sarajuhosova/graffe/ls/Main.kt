package com.sarajuhosova.graffe.ls

import org.eclipse.lsp4j.launch.LSPLauncher
import java.io.InputStream
import java.io.OutputStream

fun main() {
    start()
}

fun start(input: InputStream = System.`in`, output: OutputStream = System.out) {
    val server = GRaffeLanguageServer()
    val launcher = LSPLauncher.createServerLauncher(server, input, output)

    server.connect(launcher.remoteProxy)
    launcher.startListening()
}
