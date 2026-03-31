<p align="center">
  <img src="logo_datasphere.png" width="200"/>
  <h2 align="center"> Data Sphere </h2>
</p>

## 📈 Estrutura das Branchs

```
main -> branch principal com código estável e pronto para produção.
```
```
dev -> branch para integração contínua de novas funcionalidades em desenvolvimento.
```
```
docs -> branch para atualizações e melhorias na documentação do projeto
```
```
feature/[nome da task] -> branch para desenvolvimento de uma nova funcionalidade específica
```
```
fix -> correção de bugs e problemas encontrados no código
```

O desenvolvimento começa nas branches de trabalho — feat, fix e docs — onde cada alteração é implementada de forma isolada. Ao concluir o trabalho em qualquer uma dessas branches, o desenvolvedor abre um pull request direcionado à dev, onde o código passa por revisão antes de ser integrado. A dev funciona como ambiente de consolidação, acumulando as contribuições de todas as branches até que o conjunto de mudanças esteja estável e validado, momento em que um novo pull request é aberto da dev para a main, promovendo o código para produção.