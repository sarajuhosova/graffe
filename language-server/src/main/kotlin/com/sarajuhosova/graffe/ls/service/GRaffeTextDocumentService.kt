package com.sarajuhosova.graffe.ls.service

import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.TextDocumentService
import java.util.concurrent.CompletableFuture

object GRaffeTextDocumentService: TextDocumentService {

    override fun didOpen(p0: DidOpenTextDocumentParams) {
        TODO("Not yet implemented")
    }

    override fun didChange(p0: DidChangeTextDocumentParams) {
        TODO("Not yet implemented")
    }

    override fun didClose(p0: DidCloseTextDocumentParams) {
        TODO("Not yet implemented")
    }

    override fun didSave(p0: DidSaveTextDocumentParams) {
        TODO("Not yet implemented")
    }

    override fun definition(params: DefinitionParams):
            CompletableFuture<Either<MutableList<out Location>, MutableList<out LocationLink>>> {
        return super.definition(params)
    }

}
