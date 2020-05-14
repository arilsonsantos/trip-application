import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        name("pagamento com sucesso")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJson()
            }

            body([
                "numeroCartao": 12345678,
                "codigoSeguranca":90,
                "valorCompra":50,
            ])
            
        }

        response {
            status OK()
            headers {
                contentType applicationJson()
            }

            body([
                "mensagem": "Pagamento registrado com sucesso"
            ])
        }
    },
    Contract.make {
        name("numero do cartao invalido")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJson()
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
        name("sem saldo suficiente")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJson()
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
        name("numero do cartao nulo")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJson()
            }

            body([
                "codigoSeguranca":90,
                "valorCompra":20
            ])
        }

        response {
            status BAD_REQUEST()
            headers {
                contentType applicationJson()
            }

            body([
                "mensagem": "Argumentos inválidos",
                "erros": [
                    "numeroCartao": "Número do cartão não pode ser nulo"
                ]
            ])
        }
      },
      Contract.make {
        name("codigo de seguranca nulo")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJson()
            }

            body([
                "numeroCartao": 12345678,
                "valorCompra":20
            ])
        }

        response {
            status BAD_REQUEST()
            headers {
                contentType applicationJson()
            }

            body([
                "mensagem": "Argumentos inválidos",
                "erros": [
                    "codigoSeguranca": "Código de segurança não pode ser nulo"
                ]
            ])
        }
      }

]