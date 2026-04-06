//Index
document.getElementById('chat-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const userInput = document.getElementById('user-input').value;
    const chatWindow = document.getElementById('chat-window');

    const userMessageDiv = document.createElement('div');
    userMessageDiv.className = 'user-message';
    userMessageDiv.textContent = userInput;
    chatWindow.appendChild(userMessageDiv);

    setTimeout(function() {
        const botMessageDiv = document.createElement('div');
        botMessageDiv.className = 'bot-message';
        botMessageDiv.textContent = 'Resposta do bot';
        chatWindow.appendChild(botMessageDiv);
        chatWindow.scrollTop = chatWindow.scrollHeight;
    }, 1000);

    document.getElementById('user-input').value = '';
});

//Chamados
document.addEventListener('DOMContentLoaded', function () {
    console.log('Página de perfil carregada com sucesso.');
});

document.getElementById('novoChamadoForm').addEventListener('submit', function(event) {
    event.preventDefault(); o

    const assunto = document.getElementById('assunto').value;
    const descricao = document.getElementById('descricao').value;
    const prioridade = document.getElementById('prioridade').value;

    alert(`Chamado criado:\nAssunto: ${assunto}\nDescrição: ${descricao}\nPrioridade: ${prioridade}`);

    document.getElementById('novoChamadoForm').reset();
});

const detalhesBtns = document.querySelectorAll('.ver-detalhes-btn');
const detalhesTextarea = document.getElementById('chamadoDetalhes');
const salvarDetalhesBtn = document.getElementById('salvarDetalhes');

const chamadosDetalhes = [
    'Detalhes do problema de login',
    'Detalhes do erro no pagamento',
    'Detalhes da solicitação de acesso'
];

detalhesBtns.forEach((btn, index) => {
    btn.addEventListener('click', () => {
        detalhesTextarea.value = chamadosDetalhes[index];
        salvarDetalhesBtn.setAttribute('data-chamado-index', index);
    });
});

salvarDetalhesBtn.addEventListener('click', function() {
    const index = this.getAttribute('data-chamado-index');
    const novosDetalhes = detalhesTextarea.value;
    chamadosDetalhes[index] = novosDetalhes; 
    alert(`Detalhes do chamado #${parseInt(index) + 1} foram salvos!`);
    $('#detalhesModal').modal('hide'); 
});


//Sobre
document.addEventListener('DOMContentLoaded', function () {
    console.log('Página "Sobre o Projeto" carregada com sucesso.');
});

//Tela de Login
function showLogin() {
    const container = document.querySelector('.login-register-container');
    container.classList.remove('active');
}

function showRegister() {
    const container = document.querySelector('.login-register-container');
    container.classList.add('active');
}

//Perfil
document.getElementById('edit-profile').addEventListener('click', function() {
    alert('Função de edição de perfil em desenvolvimento.');
});



