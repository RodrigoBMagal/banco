<h1 align="center">Sistema de Banco</h1>

## Descrição do Projeto

<p align="justify"> Este projeto consiste num banco de dados </p>
Este projeto tem como objetivo criar, do zero, um sistema que simule as operações básicas de um banco digital. Ele será desenvolvido utilizando tecnologias modernas e boas práticas de engenharia de software, com foco em arquitetura RESTful, testes automatizados e organização ágil.
<br></br>
🧾 Descrição Geral<br>
O sistema permitirá o cadastro de clientes e contas bancárias, além de oferecer funcionalidades essenciais como:<br>
• Depósito <br>
• Saque <br>
• Transferência entre contas<br>
• Consulta de saldo<br>
• Geração de extrato bancário<br><br>
Cada cliente poderá possuir apenas uma conta, e todas as operações deverão respeitar regras de negócio específicas para garantir a integridade dos dados e a segurança das transações.
<br></br>
📋 Regras de Negócio<br><br>
• O saldo da conta não pode ficar negativo.<br>
• Transferências só podem ocorrer se houver saldo suficiente.<br>
• Contas de origem e destino devem ser válidas e diferentes.<br>
• O extrato deve registrar todas as movimentações (depósitos, saques, transferências).<br>
• O serviço de criação de conta deve retornar um identificador único.<br>
• Deve existir um serviço para consulta de conta por parte do cliente.<br>
• O cliente deve estar autenticado pelo Keycloak para acessar a aplicação.<br>
• As requisições devem conter um token JWT válido no header.<br>
• O token deve incluir os scopes que definem as permissões do usuário.<br>
• Cada endpoint deve exigir um scope específico para autorizar a operação.<br>
• Requisições com token inválido devem retornar 401 Unauthorized.<br>
• Requisições sem permissão devem retornar 403 Forbidden.<br><br>



🛠️ Tecnologias Utilizadas
O projeto será desenvolvido com as seguintes ferramentas e linguagens:
<br>
• Java <br>
• Spring Boot e Spring MVC para estruturação da aplicação<br>
• Maven para gerenciamento de dependências <br>
• Git para versionamento<br>
• MockMVC, JUnit e Mockito para testes automatizados<br>
• Banco de dados em container para persistência<br>
• Codecov para garantir cobertura de testes acima de 60%<br>
• Spring Security para configurar a segurança<br>
• Annotations para mapear<br>
• Keycloak dockerizado para ser usado com IAM<br>
• Algoritmos de criptografia de token<br>

🔄 Evolução: Processamento Assíncrono com Filas<br><br>
Na segunda etapa do projeto, será introduzido o conceito de processamento assíncrono utilizando Apache Kafka. A funcionalidade de transferência será modificada para que, ao receber uma requisição, o sistema envie uma mensagem para uma fila. Um consumidor processará essas mensagens em ordem (FIFO), garantindo escalabilidade e desacoplamento entre requisição e execuçãon com isso teremos novas regras.<br><br>
• A transferência deve retornar um identificador único para rastreamento.<br>
• Deve existir um serviço para consultar o status da transferência.<br>
• O consumidor Kafka deve aplicar as mesmas regras de negócio da aplicação REST.<br><br>
• Além de novos requisitos como: <br><br>
• Utilizar Kafka como broker de mensagens.<br>
• Implementar migração de banco de dados no startup da aplicação, com versionamento no código.<br>
• Garantir que o processamento das mensagens seja FIFO (First In, First Out).<br>

🔐 Fase 3: Autenticação e Autorização com Keycloak<br><br>
🎯 Objetivo<br><br>
Adicionar segurança aos endpoints da aplicação usando Spring Security, OAuth 2.0 e Keycloak como ferramenta de IAM (Identity and Access Management).<br><br>

📋 Requisitos Não Funcionais<br>
• Criar usuários no Keycloak representando personas do sistema<br>
• Realizar login via Postman ou Insomnia para obter o token<br>
• Utilizar o token nas requisições às APIs protegidas<br>
