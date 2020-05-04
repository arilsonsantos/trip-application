import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        name("PagamentoSemSaldoNotAcceptable")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJsonUtf8()
            }

            body([
                "numeroCartao": 12345678,
                "codigoSeguranca":90,
                "valorCompra":100
            ])
        }

        response {
            status NOT_ACCEPTABLE()
            headers {
                contentType applicationJson()
            }

            body([
                "mensagem": "Não há saldo suficiente"
            ])
        }
    },
        Contract.make {
        name("PagamentoCartaoInvalidoBadRequest")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJsonUtf8()
            }

            body([
                "numeroCartao": 123,
                "codigoSeguranca":90,
                "valorCompra":10
            ])
        }

        response {
            status NOT_ACCEPTABLE()
            headers {
                contentType applicationJson()
            }

            body([
                "mensagem": "Cartão inválido"
            ])
        }
    },

        Contract.make {
        name("PagamentoJsonInvalidoSemNumerocartaoBadRequest")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJsonUtf8()
            }

            body([
                "codigoSeguranca":90,
                "valorCompra":10
            ])
        }

        response {
            status BAD_REQUEST()
            headers {
                contentType applicationJson()
            }

            body([
                mensagem: "Argumentos inválidos",
                erros: [
                    numeroCartao: "Número do cartão não pode ser nulo"
                ]
            ])
        }

    },

        Contract.make {
        name("PagamentoJsonInvalidoSemCodigoSegurancaBadRequest")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJsonUtf8()
            }

            body([
                "numeroCartao": 123,
                "valorCompra":10
            ])
        }

        response {
            status BAD_REQUEST()
            headers {
                contentType applicationJson()
            }

            body([
                mensagem: "Argumentos inválidos",
                erros: [
                    codigoSeguranca: "Código de segurança não pode ser nulo"
                ]
            ])
        }

    }
]