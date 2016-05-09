#FuncProgIFCE
### Paradigmas de Programação - IFCE
Material referente a programação funcional, para apoio a disciplina de paradigmas de programação do IFCE. A linguagem escolhida para demonstrar os conceitos foi o Clojure, devido ao robusto ecosistema da JVM/JAVA que o apoia e sua sintaxe ser familiar e muito documentada, por se tratar de um LISP.

### Programação Funcional
 Paradigma que gira em torno do uso de dados imutáveis e de funções como representações matemáticas **determinística**, isto é, dada um conjunto de entradas, a mesma saída sempre será obtida, indepedente do estado global do sistema e sem propagar mudanças no mesmo(**efeitos colaterais**), ou seja, com **integridade referêncial**. Uma função se diz **pura** se possui **Transparência Referencial** e nao gera **efeitos colaterais**.

### Clojure
  Um dialeto **LISP-1**(simplificando, dentro de um mesmo **namespace** funções e variáveis não podem possuir o mesmo nome) de propósito geral criado por *Rich Hickey* que roda sobre a **JVM** e tem acesso a todo ecosistema JAVA.

  Uma lista com as funções *built-in* do clojure, com exemplos de uso, podem ser encontradas aqui: https://kimh.github.io/clojure-by-example/ 
  
  Uma lista de vários recursos, IDEs, bibliotecas e outras coisas relacionadas a clojure: https://clojure.zeef.com/vlad.bokov
  
  
  
#### Tarefa - Descrição e metodologia
  O objetivo desta tarefa é criar uma API com o compojure, que fara a leitura de um json de uma API pública, extrair 4 parametros do mesmo, processar um dos valores através de uma classe JAVA, e enviar de volta ao cliente este valor processado, um vaor interno do java e os outros 2, empacotados como JSON.
  
  **Passos**

- baixar e configurar o **Leiningen**
 - Baixe o script [lein](https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein) (ou, no Windows [lein.bat](https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein.bat));
 - Mova para algum lugar no seu $PATH (por exemplo, /usr/bin);
 - Torne-o executável ( `chmod a+x /usr/bin/lein` );
 - Execute-o ( `lein` ) e ele vai baixar o pacote de auto-instalação.
- criar um novo projeto de API com `lein new compojure tarefa`
- adicionar suporte a saida e entrada em json, conforme modificacoes aqui: [#644a2bc](https://github.com/paoloo/FuncProgIFCE/commit/644a2bce9f006725f9c83b6a634d31b014648aaf)
 - adicionar ao [project.clj](project.clj), nas dependencias(`:dependencies`), o conteudo:
 ```
[org.clojure/data.json "0.2.6"]
[ring/ring-json "0.1.2"]
[cheshire "4.0.3"]
 ```
 - adicionar, no arquivo que vai lidar com as rotas(geralmente [handler.clj](src/aula/handler.clj)), na sessão `:require`:
 ```
 [ring.middleware.json :as middleware]
 [compojure.handler :as handler]
 [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
 ```
 - mudar toda a definição da função chamada pelo ring(**app**) para utilizar o middleware JSONificador, assim:
 ```
 (def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))
 ```
 - agora o código está funcionando com um middleware JSON na entrada e saída. Para usá-lo, deve-se usar o template:
 ```
 {:body {:parametroA valorA, ..., :parametroZ valorZ }}
 ```
 - para usar em outro arquivo, adicionar apenas, no `:require`, `[clojure.data.json :as json]`. 
 - Já para fazer uma requisição http, adicionar no `:require` a lib `[clj-http.client :as client]` e utilizar como `(client/get URL)`.
- adicionar uma classe java com um metodo e variavel chamados por API, conforme aqui: [#18c4a73](https://github.com/paoloo/FuncProgIFCE/commit/18c4a739dd89530cc69dac4b4b079d0222131142)
 - adicionar ao [project.clj](project.clj) o conteudo `:java-source-paths ["src/namespace/java"]`;
 - adicionar os arquivos .java em `src/namespace/java/`;
 - se referenciar a eles como `namespace.java/recurso` onde `recurso` pode ser um metodo ou variavel.
- **PARA RODAR, EXECUTEM** `lein ring server` no diretório base. 
- O resto é com vocês. Dúvidas, só falar comigo.
