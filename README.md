# SeriesManager

:red_circle: Projeto da disciplina PDM 

Parte 1:

O projeto consiste no desenvolvimento de um aplicativo simples de lista com uso de
persistência. O aplicativo permite criar, apagar e gerenciar séries, temporadas e seus
episódios. O aplicativo deve possuir uma opção para inserção de novas séries que vão aparecer
em lista na tela principal. Na tela principal deve haver opções para que sejam adicionadas
novas, para visualizar detalhes (isto é, temporadas cadastradas) e para apagar séries
cadastradas. Cada série deve possuir um nome (que deve ser único), ano de lançamento, e
uma emissora e uma gênero que pode ser romance, aventura, terror etc. Não é obrigatório,
mas seria apreciado se o usuário também pudesse cadastrar e remover outros gêneros para
categorizar as suas séries.
Clicando na célula correspondente a série na tela principal será possível ver a lista de
temporadas (outra tela). Para cada série será possível cadastrar e gerenciar temporadas. Cada
temporada deve ter um número sequencial, ano de lançamento, e quantidade de episódios.
Para cada temporada é possível visualizar (outra tela) a lista de episódios. Será possível
cadastrar os episódios que compõem a temporada. Cada episódio deve um número
sequencial, um nome, um tempo de duração (em minutos), uma flag (para indicar se o
episódio já foi assistido ou não). Clicando num episódio específico o usuário poderá gerenciá-lo
(editá-lo ou marca-lo como já assistido).
Deve-se manter observância aos conceitos de desenvolvimento de um banco de dados. A
persistência deve ser implementada usando SQLite e o uso de uma arquitetura de software
(mesmo que MVC) é indicado.

:movie_camera: O link para a gração da aprensentação do projeto está disponivel em http://sc.com.ly/show/52d4efe2-da04-4257-8d37-39bdb03c6511

Parte 2:

A segunda parte do aplicativo avaliativo se trata de uma continuação, mas com especificação e
requisitos diferentes, do projeto SeriesManager. Para este momento avaliativo será necessário
implementar duas novas funcionalidades. A primeiro funcionalidade se refere à persistência
dos dados no Banco de Dados de Tempo Real (Realtime Database) do Firebase. Atente-se ao
fato de que as regras de acesso devem manter o banco disponível pelo menos até a segunda
semana de dezembro para fins de correção. A segunda funcionalidade requerida é
implementação de um mecanismo de autenticação usando e-mail e senha junto ao Firebase.
Não é necessário implementar outros métodos de autenticação. Caso julgue necessário é
possível também configurar as regras de acesso do Banco de Dados de Tempo Real para que
somente usuários autenticados possam fazer uso do banco.
