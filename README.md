# Sistema Acadêmico - Documentação e API SEED

Bem-vindo ao repositório do Sistema Acadêmico. 

Abaixo encontra-se a documentação oficial e exaustiva para realizar testes de integração via Postman (ou outra ferramenta de API Client), baseada nos dados iniciais (SEED) populados pelas migrations do Flyway (`V2` a `V5`).

---

## 1. Dados Iniciais para Teste (Seed IDs)

Estes são os IDs reais inseridos pelo Flyway no banco de dados, prontos para uso sem quebrar chaves de integridade relacional.

### 👤 Usuários
| ID (`usuarioId`) | Nome | Perfil | Observação |
| :--- | :--- | :--- | :--- |
| **1** | Coordenador Geral | `COORDENADOR` | Tem acesso total a cadastros e exclusões lógicas. |
| **2** | Professor Carlos | `PROFESSOR` | ID do Professor: `1`. Pode lançar Notas e Faltas. |
| **3** | Aluno Exemplo | `ALUNO` | ID do Aluno: `1` (Matrícula: 20261001). |
| **4** | Professora Maria | `PROFESSOR` | ID do Professor: `2`. |
| **5** | Joao Silva | `ALUNO` | ID do Aluno: `2` (Matrícula: 20261002). |
| **6** | Ana Souza | `ALUNO` | ID do Aluno: `3` (Matrícula: 20261003). |
| **7** | Aluno Teste Postman| `ALUNO` | Sem `alu_nr_id` vinculado (Excelente para teste de POST Aluno). |

### 🎓 Cursos
| ID | Nome |
| :--- | :--- |
| **1** | Sistemas de Informação |
| **2** | Ciência da Computação |
| **3** | Engenharia de Software |

### 📚 Disciplinas
| ID | Nome | Código |
| :--- | :--- | :--- |
| **1** | Estrutura de Dados | ED101 |
| **2** | Programação Web | PW01 |
| **3** | Banco de Dados | BD01 |

### 📅 Períodos Letivos
| ID | Descrição |
| :--- | :--- |
| **1** | 2026.1 |
| **2** | 2025.2 |

### 🏫 Turmas
| ID Turma | Disciplina | Período Letivo | Professor |
| :--- | :--- | :--- | :--- |
| **1** | Estrutura de Dados (ID 1) | 2026.1 (ID 1) | Professor Carlos (ID 1) |
| **2** | Programação Web (ID 2) | 2026.1 (ID 1) | Professor Carlos (ID 1) |
| **3** | Banco de Dados (ID 3) | 2026.1 (ID 1) | Professora Maria (ID 2) |

### 📋 Matrículas
| ID (`matriculaTurmaId`) | Aluno | Turma |
| :--- | :--- | :--- |
| **1** | Aluno Exemplo (ID 3) | Turma 1 |
| **2** | Aluno Exemplo (ID 3) | Turma 2 |
| **3** | Joao Silva (ID 5) | Turma 1 |
| **4** | Joao Silva (ID 5) | Turma 2 |
| **5** | Ana Souza (ID 6) | Turma 1 |
| **6** | Ana Souza (ID 6) | Turma 3 |

### 💬 Chats
| ID (`chatTurmaId`) | Turma Relacionada |
| :--- | :--- |
| **1** | Turma 1 (Estrutura de Dados) |
| **2** | Turma 2 (Programação Web) |
| **3** | Turma 3 (Banco de Dados) |

### 📝 Notas e Faltas (Alguns exemplos já inseridos para Matrícula ID 1)
| Matrícula ID | Lançamentos Feitos |
| :--- | :--- |
| **1** | Notas: Prova 1 (7.5), Prova 2 (8.0), Trabalho (9.0) <br> Faltas: 2 em 2026-03-10, 1 em 2026-04-05 |

---

## 2. Autenticação e Perfis

Todas as requisições que alteram estado (POST, PUT, DELETE) e algumas de GET exigem a passagem do **Header** `usuario-id` simulando o usuário logado no sistema. 

- O valor `1` sempre concederá privilégios de **COORDENADOR**.
- O valor `2` e `4` concederá privilégios de **PROFESSOR**.
- Os valores `3`, `5`, `6` e `7` representam acessos de **ALUNO**.

---

## 3. Endpoints da API (CRUD Completo)

### 🟢 1. Usuários (`/api/usuarios`)

*   `POST /api/usuarios` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "nome": "Teste", "email": "teste@email.com", "login": "teste", "senha": "123", "perfil": "PROFESSOR" }
    ```
*   `GET /api/usuarios` (Acesso livre)
*   `GET /api/usuarios/{id}` (Acesso livre)
*   `PUT /api/usuarios/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "nome": "Atualizado", "email": "up@email.com", "login": "teste", "senha": "123", "perfil": "PROFESSOR" }
    ```
*   `DELETE /api/usuarios/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`

### 🟢 2. Cursos (`/api/cursos`)

*   `POST /api/cursos` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "nome": "Design", "codigoSuap": "DS-001" }
    ```
*   `GET /api/cursos` (Acesso livre)
*   `GET /api/cursos/{id}` (Acesso livre)
*   `PUT /api/cursos/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
*   `DELETE /api/cursos/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`

### 🟢 3. Disciplinas (`/api/disciplinas`)

*   `POST /api/disciplinas` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "nome": "UX Design", "codigo": "UX01", "codigoSuap": "SUAP-UX", "cargaHoraria": 40 }
    ```
*   `GET /api/disciplinas` (Acesso livre)
*   `GET /api/disciplinas/{id}` (Acesso livre)
*   `PUT /api/disciplinas/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
*   `DELETE /api/disciplinas/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`

### 🟢 4. Períodos Letivos (`/api/periodos-letivos`)

*   `POST /api/periodos-letivos` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "ano": 2027, "semestre": 1, "descricao": "2027.1" }
    ```
*   `GET /api/periodos-letivos` (Acesso livre)
*   `GET /api/periodos-letivos/{id}` (Acesso livre)
*   `PUT /api/periodos-letivos/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
*   `DELETE /api/periodos-letivos/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`

### 🟢 5. Professores (`/api/professores`)

*   `GET /api/professores/{professorId}/turmas` (Acesso livre) - *Traz todas as turmas do professor.*
*   `GET /api/professores/{professorId}/turmas/{turmaId}/alunos` (Acesso livre) - *Traz as matrículas daquela turma.*

### 🟢 6. Alunos (`/api/alunos`)

*   `POST /api/alunos` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "usuarioId": 7, "cursoId": 1, "matricula": "20261010" }
    ```
    *(Nota: `usuarioId: 7` é o 'Aluno Teste Postman' que ainda não tem aluno vinculado. Evita erro de chave `OneToOne`.)*
*   `GET /api/alunos` (Acesso livre)
*   `GET /api/alunos/{id}` (Acesso livre)
*   `PUT /api/alunos/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
*   `DELETE /api/alunos/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
*   `GET /api/alunos/{alunoId}/turmas` (Header `usuario-id: 1` ou do próprio aluno)
*   `GET /api/alunos/{alunoId}/notas` (Header `usuario-id: 1` ou do próprio aluno)
*   `GET /api/alunos/{alunoId}/faltas` (Header `usuario-id: 1` ou do próprio aluno)
*   `GET /api/alunos/{alunoId}/situacao` (Header `usuario-id: 1` ou do próprio aluno) - *Gera o boletim completo.*

### 🟢 7. Turmas (`/api/turmas`)

*   `POST /api/turmas` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "disciplinaId": 3, "professorId": 1, "periodoLetivoId": 1, "descricao": "Turma Extra", "codigoSuap": "SUAP-EXTRA" }
    ```
*   `GET /api/turmas` (Acesso livre)
*   `GET /api/turmas/{id}` (Acesso livre)
*   `PUT /api/turmas/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
*   `DELETE /api/turmas/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`

### 🟢 8. Matrículas (`/api/matriculas`)

*   `POST /api/matriculas` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`
    ```json
    { "alunoId": 3, "turmaId": 2 }
    ```
    *(Nota: A Ana Souza (`alunoId: 3`) ainda não está na Turma de Programação Web (`turmaId: 2`). Teste 100% seguro contra duplicidades!)*
*   `GET /api/matriculas/{id}` (Acesso livre)
*   `GET /api/matriculas/aluno/{alunoId}` (Acesso livre)
*   `GET /api/matriculas/turma/{turmaId}` (Acesso livre)
*   `DELETE /api/matriculas/{id}` (Apenas `COORDENADOR`) - Header: `usuario-id: 1`

### 🟢 9. Notas (`/api/notas`)

*   `POST /api/notas` (Apenas `PROFESSOR` ou `COORDENADOR`) - Header: `usuario-id: 2`
    ```json
    { "matriculaTurmaId": 5, "descricao": "Avaliação Parcial", "valor": 8.5 }
    ```
*   `GET /api/notas/matricula/{matriculaTurmaId}` (Acesso livre)
*   `DELETE /api/notas/{id}` (Apenas `PROFESSOR` ou `COORDENADOR`) - Header: `usuario-id: 2`

### 🟢 10. Faltas (`/api/faltas`)

*   `POST /api/faltas` (Apenas `PROFESSOR` ou `COORDENADOR`) - Header: `usuario-id: 2`
    ```json
    { "matriculaTurmaId": 5, "data": "2026-06-01", "quantidade": 2, "observacao": "Gripe" }
    ```
*   `GET /api/faltas/matricula/{matriculaTurmaId}` (Acesso livre)
*   `DELETE /api/faltas/{id}` (Apenas `PROFESSOR` ou `COORDENADOR`) - Header: `usuario-id: 2`

### 🟢 11. Mensagens / Chat (`/api/mensagens`)

*   `POST /api/mensagens` - Header: `usuario-id: 3` (Simulando o Aluno Exemplo)
    ```json
    { "chatTurmaId": 2, "remetenteId": 3, "texto": "Professor, pode tirar uma dúvida de JS?" }
    ```
*   `GET /api/mensagens/chat/{chatTurmaId}` - Header: `usuario-id: 3`
*   `DELETE /api/mensagens/{id}` (Apenas o **Autor** ou o **Coordenador**) - Header: `usuario-id: 3` (Para apagar a própria mensagem)

---

## 4. Como rodar com Docker (Deploy Completo Integrado)

Para subir **toda a infraestrutura** (Banco de Dados PostgreSQL, Backend Spring Boot e Frontend Flutter) com apenas um comando de forma isolada e pronta para uso:

### Pré-requisitos
- Ter o **Docker** e o **Docker Compose** instalados na sua máquina.

### Passo a passo

1. Abra o menu **Iniciar do Windows**, digite **cmd** e aperte Enter para abrir o **Prompt de Comando**.
2. Dentro do prompt de comando, digite o seguinte comando e aperte Enter para navegar até a pasta do projeto:
   ```cmd
   cd c:\Users\Rony\IdeaProjectsJava_Bsi\sistema-academico
   ```
3. (Opcional) Você pode definir as credenciais do banco exportando as variáveis `USUARIO` e `SENHA` no seu terminal ou criando um arquivo `.env` na raiz. Se omitidas, o padrão será `postgres` / `postgres`.
4. Rode o seguinte comando para construir as imagens e iniciar os contêineres:

   ```bash
   docker-compose up --build -d
   ```
   *(A primeira execução irá baixar as dependências do Java, o SDK do Flutter, gerar os models estáticos e compilar tudo. Isso pode levar alguns minutos).*

5. **Acompanhe os logs** (Opcional):
   ```bash
   docker-compose logs -f
   ```

### URLs de Acesso

Assim que os contêineres estiverem de pé e rodando:

- 🌐 **Frontend (Aplicação Web)**: Acesse no seu navegador `http://localhost` (na porta padrão 80).
- ⚙️ **Backend (API Spring Boot)**: Disponível e escutando na porta `http://localhost:8080`.
- 🗄️ **Banco de Dados (PostgreSQL)**: Disponível para conexões via DBeaver/PgAdmin em `localhost:5432` (User: `postgres`, Pass: `postgres`, BD: `projeto_academico`).

### Como parar os contêineres

Para desligar todos os serviços e remover os contêineres:
```bash
docker-compose down
```
*(Seus dados do PostgreSQL estão salvos de forma persistente em um volume local. Caso queira limpar tudo, incluindo o volume de dados, adicione a flag `-v`: `docker-compose down -v`).*
