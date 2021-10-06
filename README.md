# Regras Simplificadas do Jogo de Damas


1. Objetivo: imobilizar ou capturar todas as peças do adversário.
2. ~~O Jogo de Damas é praticado em um tabuleiro de 64 casas~~ ou de 100 casas, claras e escuras.
3. ~~A grande diagonal (escura), deve ficar sempre à esquerda de cada jogador.~~
4. ~~O lance inicial cabe sempre ao jogador que estiver com as peças claras.~~
5. A pedra anda só para frente, uma casa de cada vez.
6. Quando a pedra atinge a última linha do tabuleiro, concluindo o lance nas casas de coroação, ela é promovida à Dama.
7. A Dama é uma peça de movimentos mais amplos.
8. A Dama anda para frente e para trás, quantas casas quiser.
9. A captura não é obrigatória. Não existe sopro. Duas ou mais peças juntas, na mesma diagonal não podem ser capturadas.
10. A pedra captura a Dama e a Dama captura a pedra. Pedra e Dama têm o mesmo valor para capturarem ou serem capturadas.
11. A pedra e a Dama podem capturar, tanto para frente, como para trás, uma ou mais peças.
12. Se no mesmo lance se apresentar mais de uma possibilidade de capturar peças, não é obrigatório executar o lance que capture o maior número de peças (Lei da Maioria).
13. A pedra que durante o lance de captura de várias peças, apenas passe por qualquer casa de coroação, sem aí parar, é opcional ser promovida à Dama.
14. Na execução do lance de captura, é permitido passar mais de uma vez pela mesma casa vazia.
15. Na execução do lance de captura, não é permitido capturar a mesma peça mais de uma vez mas é permitido as peças capturadas poderem ser retiradas do tabuleiro antes de completar o lance de captura.
16. Opcional: Empate – 64 casas - Após 20 (vinte) lances sucessivos de Damas de cada jogador, sem captura ou deslocamento de pedra, a partida é declarada empatada. 100 casas - Após 25 (vinte e cinco) lances sucessivos de Damas de cada jogador, sem captura ou deslocamento de pedra, a partida é declarada empatada.
17. A dama no último movimento de captura pode parar em qualquer casa livre na diagonal em que está capturando. A dama não é obrigada a parar na casa seguinte após a última peça capturada.
18. Opcional: Finais de: 2 damas contra 2 damas; 2 damas contra uma; 2 damas contra uma dama e uma pedra; uma dama contra uma dama e uma dama contra uma dama e uma pedra, são declarados empatados após 5 lances de cada jogador.

Feito por Lucas Regis Alves e Luiz Gabriel Alcântara Pontes de Lemos

Mudancas em branch "Testando1":
    - Esse detalhe adicional aqui no README
    - Mudei de PEDRA_BRANCA ou PEDRA_VERMELHA para 0 e 2 respectivamente, quando colocando pecas novas no jogo
    - limitacao de movimento para que pecas se movam diagonalmente
    - turnoSwitch agora é vezBranca em "Jogo" ao inves de "JanelaPrincipal", mudar a localização ajudou a resolver um problema
    -