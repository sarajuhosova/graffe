package com.sarajuhosova.graffe.ls

import com.sarajuhosova.graffe.ls.service.GRaffeTextDocumentService
import com.sarajuhosova.graffe.ls.service.GRaffeWorkspaceService
import org.eclipse.lsp4j.InitializeParams
import org.eclipse.lsp4j.InitializeResult
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.LanguageServer
import java.util.concurrent.CompletableFuture

class GRaffeLanguageServer: LanguageServer, LanguageClientAware {

    private var client: LanguageClient? = null

    override fun connect(client: LanguageClient?) {
        this.client = client
    }

    override fun initialize(p0: InitializeParams): CompletableFuture<InitializeResult> {
        TODO("Not yet implemented")
    }

    override fun shutdown(): CompletableFuture<Any> {
        TODO("Not yet implemented")
    }

    override fun exit() {
        TODO("Not yet implemented")
    }

    override fun getTextDocumentService(): GRaffeTextDocumentService = GRaffeTextDocumentService

    override fun getWorkspaceService(): GRaffeWorkspaceService = GRaffeWorkspaceService

}
