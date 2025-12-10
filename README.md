
# **Alvarium App** - Acompanhamento, Conversão e Favoritos de Criptomoedas

Aplicativo mobile desenvolvido em **Kotlin** com **Jetpack Compose**, oferecendo uma interface moderna, fluida e intuitiva.  
Permite que qualquer usuário acompanhe em tempo real os valores das principais criptomoedas, converta moedas para sua moeda local e salve suas favoritas para fácil acompanhamento  tudo de forma simples, acessível e com visual inspirado em apps fintech.

---

## 👥 Integrantes da equipe

- *Anthony Vinicius de Brito Barros*
- *Luiz Gabriel Buarque Vasconcelos*
- *Vinicius José de Arruda*

---

## 🎯 Problema

Com o crescimento do mercado de criptomoedas, muitas pessoas desejam acompanhar preços, realizar conversões e manter controle das suas moedas preferidas sem complicação.

---

## 🚀 Funcionalidades

- ✔ Exibição das principais criptomoedas (BTC, ETH, SOL, BNB e mais)
- ✔ Tela de detalhes com:
  - Gráfico de preços
  - Variação 
- ✔ Conversão de criptomoedas para moedas fiduciárias
- ✔ Lista de favoritos
- ✔ Busca inteligente com filtragem instantânea
- ✔ Tema escuro padrão com visual fintech
- ✔ Navegação simples

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Função no Projeto |
|-----------|------------------|
| Kotlin | Linguagem principal |
| Jetpack Compose | Interface declarativa moderna |
| ViewModel + StateFlow | Gerenciamento de estado |
| Retrofit | Comunicação HTTP |
| API CoinGecko | Fonte de dados de mercado |

📌 Documentação da API CoinGecko:  
https://www.coingecko.com/en/api/documentation

---

## 📂 Estrutura do Projeto
```
app/
├── data/
│ ├── local/
│ ├── mapper/
│ ├── remote/
│ └── repository/
├── domain/
│ ├── model/
│ └── repository/
├── ui/
│ ├── components/
│ ├── navigation/
│ ├── screens/
│ └── theme/
├── viewmodel/
```
---

## 🏁 Instruções de Instalação & Execução
```bash
1️⃣ Clone este repositório:

git clone https://github.com/AnthonyVinicius/Projeto_Mobile.git

2️⃣ Abra o projeto no Android Studio

3️⃣ Aguarde o Gradle sincronizar

4️⃣ Execute o app em um emulador ou dispositivo físico com Android 8+

⚠ É necessário acesso à internet para consultar preços em tempo real!
```
---
## 📸 Capturas de Tela

| Home | Detalhes | Favoritos | Conversão |
|------|---------|-----------|-----------|
| ![Home](./Alvarium/screenshots/home.jpeg) | ![Detalhes](./Alvarium/screenshots/details.jpeg) | ![Favoritos](./Alvarium/screenshots/favorites.jpeg) | ![Conversão](./Alvarium/screenshots/convert.jpeg) |
