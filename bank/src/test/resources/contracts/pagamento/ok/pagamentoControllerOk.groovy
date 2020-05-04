import org.springframework.cloud.contract.spec.Contract

[
    Contract.make {
        name("PagamentoOk")
        request {
            method 'POST'
            url("/pagamentos")
            headers {
                contentType applicationJsonUtf8()
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
    }
]