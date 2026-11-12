# Homologação — Cadastro e gestão de solicitações de acesso

Implementação isolada na branch `feat/solicitacoes-acesso-cursos-admin-20261112`.

## Dependência
A API `RafaelPinheiroCosta/carteirinha-digital-api` **precisa estar na branch de mesmo nome**. Não testar este Android com a API da branch `master`, que não possui as rotas novas.

## Como iniciar
1. No backend, configurar `APP_BOOTSTRAP_ADMIN_LOGIN` e `APP_BOOTSTRAP_ADMIN_PASSWORD` (senha >=12 caracteres), executar `mvn test` e depois `mvn spring-boot:run`.
2. No Android, executar `git switch feat/solicitacoes-acesso-cursos-admin-20261112`, depois `.\gradlew.bat :app:assembleDebug`.
3. Iniciar o emulador Android; a URL local já é `http://10.0.2.2:8080/`.
4. Login antigo de aluno e professor deve continuar funcionando.

## Roteiro manual
- Login > “Não tenho conta — solicitar acesso”.
- Aluno: informar nome, login, senha de no mínimo 8 caracteres, escolher perfil e curso vindo da API; enviar.
- Antes da aprovação, tentar login: deve exibir “Cadastro aguardando aprovação do administrador”.
- Administrador: autenticar com a conta do ambiente; Home > “Gestão de Solicitações de Acesso”.
- Aba **Alunos**: abrir candidato, conferir perfil e curso solicitados (somente leitura), selecionar turma **do curso informado pelo usuário** e matrícula institucional; aprovar. Conferir login subsequente.
- Aba **Professores**: abrir candidato, conferir o perfil de professor (somente leitura), marcar múltiplas combinações Turma+UC; aprovar. Conferir que as turmas/UCs aparecem nas telas do professor.
- Rejeitar outro cadastro; confirmar exclusão e conferir ausência na lista.
- Confirmar que um token de aluno ou professor não acessa `/admin/**`.
- Verificar Logcat e tráfego HTTP, sem expor token ou senha em gravações destinadas aos alunos.

## Dependências e observações
- A API do experimento usa o catálogo acadêmico existente como fonte das opções (turmas/UCs já associadas ao professor inicial).
- A API H2 está em modo `create-drop`: ao reiniciar, dados cadastrados e aprovados somem. Isso é próprio do ambiente didático atual.
- Não fazer merge em `master` nem sincronizar `CarteirinhaDigital2DEVEST` até concluir compilação, testes automatizados e fluxo manual.
- Os testes não foram executados automaticamente pelo simples fato de existir este documento.

## Refatoração MVVM (12/11/2026)

O administrador **não escolhe o perfil**. O cadastro público registra ALUNO/PROFESSOR e, para ALUNO, o curso. Na aprovação a API usa o perfil e o curso **persistidos**; não recebe perfil/curso do cliente administrativo.

As duas features seguem o modelo didático de `feature/aluno/unidadescurriculares`:

- `feature/cadastro/data/remote/dto` e `service`: payload e interface Retrofit públicos.
- `feature/cadastro/data/repository`: `ApiCadastroRepositoryImpl`, convertendo domínio em DTO.
- `feature/cadastro/domain/model` e `repository`: `SolicitacaoCadastro` e `CadastroRepository`.
- `feature/cadastro/presentation`: `CadastroUiState`, `CadastroViewModel`, `factory/CadastroViewModelFactory` e `screen/CadastroScreen` + `CadastroContent`.
- `feature/admin/data/remote/dto` e `service`: DTOs de pendências, catálogo e aprovação.
- `feature/admin/data/repository`: `ApiGestaoSolicitacoesRepositoryImpl`, mapeando DTOs para o domínio.
- `feature/admin/domain/model` e `repository`: `SolicitacaoPendente`, `TurmaCatalogo`, `AtribuicaoDocente`, `AprovacaoSolicitacao` e interface.
- `feature/admin/presentation`: `GestaoSolicitacoesUiState`, `GestaoSolicitacoesViewModel`, `factory`, `screen` e `component`.
- `core/model/PerfilSolicitado`: enum compartilhado apenas entre os fluxos de Cadastro e Administração.

**A tela não mantém estado de negócio com `remember`:** candidato selecionado, aba, turma, matrícula, atribuições, confirmação de exclusão, erro e loading pertencem ao UiState. O `rememberScrollState` é apenas estado visual do Compose.

Os novos testes unitários estão em `feature/admin/presentation/GestaoSolicitacoesUiStateTest` e `feature/admin/data/remote/dto/AprovarCadastroDtoTest`.

No Android, use:

```powershell
.\gradlew.bat :app:testDebugUnitTest :app:assembleDebug
```

Na API, use `mvn test`. A compilação/testes precisam ser executados localmente antes da homologação; não foram executados no GitHub durante esta alteração.
