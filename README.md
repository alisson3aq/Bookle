Bookle
======

Sistema Gerenciamento Acadêmico

Este projeto foi desenvolvido como trabalho da disciplina de Programação Orientada a Objetos do curso Sistemas de Informação da UFG. O sistema tem como objetivo disponibilizar ao aluno a pesquisa de livros sugeridos pelo professor.

Criadores @author
======
- Kélvin Santiago (http://www.kelvinsantiago.com.br)
- Rhuan Pablo
- Rodrigo Lima
- Jean Matsunaga

Características 
======
* Login
* Alterar Senha
* Pesquisar Livros Sugeridos
* CRUD Usuários
* CRUD Cursos
* CRUD Disciplinas
* CRUD Livros
* Relatórios Usuários 
* Relatórios Cursos
* Relatórios Disciplinas
* Relatórios Livros

Sobre do Desenvolvimento 
======
O sistema foi desenvolvido em JAVA utilizando o IDE NetBeans, foi utilizado a biblioteca iReport para a geração dos relatórios. Como persistencia dos dados foi utilizado o banco de dados MySQL.

Como utilizar o sistema
======
1. Abra o NetBeans, clique no menu "EQUIPE" > "GIT" e clique em "CLONAR".
2. Coloque o caminho do projeto bookle "https://github.com/kelvinsleonardo/Bookle".
3. Após clonado e aberto o projeto, clique nas bibliotecas do projeto e crie uma nova biblioteca com o nome "iReport".
4. E adicione todos os .Jar que estão nas pastas:
  - BookleProjeto\library\iReport-5.5.1\ireport\libs\
  - BookleProjeto\library\iReport-5.5.1\ireport\modules\ext\
5. Instale o banco de dados MySQL na sua máquina.
6. Atribua a senha "root" ao usuário "root" do MySQL.
7. Crie uma base de dados com o nome de "bookle".
8. Importe para seu banco de dados as tabelas e os dados com o arquivo:
  - BookleProjeto\src\br\com\infofix\bookle\bancodedados\bookle.sql
9. Feito esses passos é só executar o projeto.

