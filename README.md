# Sistema Acadêmico - Documentação e API SEED

Bem-vindo ao repositório do Sistema Acadêmico. 

Abaixo você encontra a documentação oficial para realizar testes de integração via Postman (ou outra ferramenta de API Client), baseada nos dados iniciais (SEED) populados pelas migrations do Flyway (`V2` a `V5`).

---

## 1. Dados Iniciais para Teste (Seed IDs)

Estes são os IDs reais inseridos pelo Flyway no banco de dados, que já estão prontos para uso.

### 👤 Usuários
| ID (`usuarioId`) | Nome | Perfil | Observação |
| :--- | :--- | :--- | :--- |
| **1** | Coordenador Geral | `COORDENADOR` | Tem acesso total a cadastros e exclusões lógicas. |
| **2** | Professor Carlos | `PROFESSOR` | ID do Professor: `1`. Pode lançar Notas e Faltas. |
| **3** | Aluno Exemplo | `ALUNO` | ID do Aluno: `1` (Matrícula: 20261001). Só consulta próprios dados. |
| **5** | Joao Silva | `ALUNO` | ID do Aluno: `2` (Matrícula: 20261002). |

### 🏫 Turmas e Disciplinas
| ID Turma | Disciplina | Período Letivo | Professor |
| :--- | :--- | :--- | :--- |
| **1** | Estrutura de Dados (ID 1) | 2026.1 (ID 1) | Professor Carlos (ID 1) |
| **2** | Programação Web (ID 2) | 2026.1 (ID 1) | Professor Carlos (ID 1) |

### 📋 Matrículas
| ID (`matriculaTurmaId`) | Aluno | Turma |
| :--- | :--- | :--- |
| **1** | Aluno Exemplo (ID Aluno 1) | Turma 1 (Estrutura de Dados) |
| **2** | Aluno Exemplo (ID Aluno 1) | Turma 2 (Programação Web) |

### 💬 Chats
| ID (`chatTurmaId`) | Turma Relacionada |
| :--- | :--- |
| **1** | Turma 1 (Estrutura de Dados) |
| **2** | Turma 2 (Programação Web) |

### 📝 Notas (Alguns exemplos já inseridos)
| ID | Matrícula Turma | Aluno | Descrição | Valor |
| :--- | :--- | :--- | :--- | :--- |
| **1** | 1 | Aluno Exemplo | Prova 1 | 7.50 |
| **2** | 1 | Aluno Exemplo | Prova 2 | 8.00 |
| **3** | 1 | Aluno Exemplo | Trabalho Final | 9.00 |

### ❌ Faltas (Alguns exemplos já inseridos)
| ID | Matrícula Turma | Aluno | Data | Quantidade | Justificativa / Observação |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1** | 1 | Aluno Exemplo | 2026-03-10 | 2 | Ausencia justificada |
| **2** | 1 | Aluno Exemplo | 2026-04-05 | 1 | N/A |

---

## 2. Endpoints da API

Abaixo encontram-se os principais fluxos documentados para envio de JSON e configuração de Headers no Postman.

### 🟢 Usuários (`/api/usuarios`)

**1. Cadastrar Usuário**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/usuarios`
* **Autorização:** Apenas `COORDENADOR`
* **Headers:** 
  * `usuario-id`: `1`
* **Body (JSON):**
```json
{
  "nome": "Novo Usuário",
  "email": "novo@campus.edu.br",
  "login": "novo.user",
  "senha": "123",
  "perfil": "PROFESSOR"
}
```

**2. Listar Todos os Usuários**
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/usuarios`
* **Autorização:** Sem restrição explícita de perfil.

---

### 🟢 Alunos (`/api/alunos`)

**1. Cadastrar Aluno**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/alunos`
* **Autorização:** Apenas `COORDENADOR`
* **Headers:** 
  * `usuario-id`: `1`
* **Body (JSON):** *(Nota: O `usuarioId` no body precisa ser de um usuário existente que ainda não seja aluno. O `cursoId` 1 representa Sistemas de Informação)*
```json
{
  "usuarioId": 7,
  "cursoId": 1,
  "matricula": "20261010"
}
```

**2. Boletim / Situação do Aluno**
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/alunos/1/situacao`
* **Autorização:** `COORDENADOR`, `PROFESSOR`, ou o próprio `ALUNO` logado (Header `usuario-id` deve pertencer ao próprio Aluno se o perfil for ALUNO).
* **Headers:** 
  * `usuario-id`: `3` (O próprio Aluno Exemplo consultando a si mesmo) ou `1` (Coordenador).
* **Resposta Esperada (200 OK):**
```json
[
  {
    "turmaId": 1,
    "nomeDisciplina": "Estrutura de Dados",
    "descricaoTurma": "Turma A - Estrutura de Dados",
    "periodoLetivo": "2026.1",
    "mediaNotas": 8.17,
    "totalFaltas": 3,
    "situacao": "CURSANDO"
  }
]
```

---

### 🟢 Turmas (`/api/turmas`)

**1. Cadastrar Turma**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/turmas`
* **Autorização:** Apenas `COORDENADOR`
* **Headers:** 
  * `usuario-id`: `1`
* **Body (JSON):** *(Nota: `disciplinaId` 1, `professorId` 1 e `periodoLetivoId` 1 devem existir)*
```json
{
  "disciplinaId": 1,
  "professorId": 1,
  "periodoLetivoId": 1,
  "descricao": "Turma B - Estrutura de Dados",
  "codigoSuap": "SUAP-TURMA-05"
}
```

**2. Listar Todas as Turmas**
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/turmas`
* **Autorização:** Sem restrição explícita.

---

### 🟢 Matrículas (`/api/matriculas`)

**1. Matricular Aluno em Turma**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/matriculas`
* **Autorização:** Apenas `COORDENADOR`
* **Headers:** 
  * `usuario-id`: `1`
* **Body (JSON):** *(Ex: Matriculando João Silva na Turma 2)*
```json
{
  "alunoId": 2,
  "turmaId": 2
}
```

---

### 🟢 Notas (`/api/notas`)

**1. Lançar Nota**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/notas`
* **Autorização:** Apenas `PROFESSOR` ou `COORDENADOR`
* **Headers:** 
  * `usuario-id`: `2` (Professor Carlos)
* **Body (JSON):** *(Ex: Lançando nota para a Matrícula 1 do Aluno Exemplo)*
```json
{
  "matriculaTurmaId": 1,
  "descricao": "Prova 3",
  "valor": 8.5
}
```

**2. Buscar Notas por Matrícula**
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/notas/matricula/1`
* **Autorização:** Sem restrição explícita (qualquer usuário logado pode visualizar se conhecer o ID da matrícula).

---

### 🟢 Faltas (`/api/faltas`)

**1. Lançar Falta**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/faltas`
* **Autorização:** Apenas `PROFESSOR` ou `COORDENADOR`
* **Headers:** 
  * `usuario-id`: `2` (Professor Carlos)
* **Body (JSON):**
```json
{
  "matriculaTurmaId": 1,
  "data": "2026-05-18",
  "quantidade": 2,
  "observacao": "Ausência por motivo de saúde"
}
```

---

### 🟢 Chat da Turma (`/api/mensagens`)

**1. Enviar Mensagem**
* **Método:** `POST`
* **URL:** `http://localhost:8080/api/mensagens`
* **Autorização:** Sem restrição explícita de perfil, mas exige remetente válido.
* **Headers:** 
  * `usuario-id`: `3` (Aluno Exemplo enviando a mensagem)
* **Body (JSON):** *(Ex: Aluno 3 enviando mensagem no Chat 1)*
```json
{
  "chatTurmaId": 1,
  "remetenteId": 3,
  "texto": "Bom dia professor, qual será o assunto da próxima aula?"
}
```

**2. Listar Mensagens do Chat**
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/mensagens/chat/1`
* **Autorização:** Apenas exige que o solicitante exista no banco.
* **Headers:** 
  * `usuario-id`: `3`

**3. Apagar/Inativar Mensagem**
* **Método:** `DELETE`
* **URL:** `http://localhost:8080/api/mensagens/1`
* **Autorização:** Apenas `COORDENADOR` ou o próprio `AUTOR` da mensagem.
* **Headers:** 
  * `usuario-id`: `3` (O autor apagando a própria mensagem) ou `1` (Coordenador).
