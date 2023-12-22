// desafio.kt

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Aluno(
        val nome: String,
        val sobrenome: String,
        val email: String,
        val senha: String
)

data class ConteudoEducacional(
        val nome: String,
        val cargaHorariaTotal: Int,
        val cargaHorariaVideos: Int,
        val cargaHorariaAtividades: Int
)

class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    private val inscritos = mutableSetOf<Aluno>()

    fun matricular(alunos: List<Aluno>) {
        val novosInscritos = alunos.filter { !inscritos.contains(it) }
        inscritos.addAll(novosInscritos)
        novosInscritos.forEach {
            println("Olá ${it.nome} ${it.sobrenome}, você foi matriculado na formação $nome. Bem-vindo(a) à DIO!")
        }
    }

    override fun toString(): String {
        return "Formacao(nome='$nome', nivel=$nivel, conteudos=$conteudos, inscritos=$inscritos)"
    }
}

class Dio {
    private val formacoes = mutableListOf<Formacao>()

    fun cadastrarFormacao(formacao: Formacao) {
        formacoes.add(formacao)
        println("Formação ${formacao.nome} foi cadastrada na DIO. Prepare-se para uma jornada incrível!")
    }

    fun listarFormacoes() {
        println("Formações disponíveis na DIO:")
        formacoes.forEach {
            println("- ${it.nome}, Nível: ${it.nivel}")
        }
    }
}

fun main() {
    val dio = Dio()

    val aluno1 = Aluno("João", "Silva", "joao@example.com", "senha123")
    val aluno2 = Aluno("Maria", "Santos", "maria@example.com", "senha456")
    val aluno3 = Aluno("Carlos", "Souza", "carlos@example.com", "senha789")

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 120, 60, 60)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos em Kotlin", 180, 90, 90)

    val formacaoKotlinBasico = Formacao("Kotlin Básico", Nivel.BASICO, listOf(conteudo1, conteudo2))
    val formacaoJavaIntermediario = Formacao("Java Intermediário", Nivel.INTERMEDIARIO, listOf(conteudo1))

    dio.cadastrarFormacao(formacaoKotlinBasico)
    dio.cadastrarFormacao(formacaoJavaIntermediario)

    dio.listarFormacoes()

    // Matrícula em uma formação
    formacaoKotlinBasico.matricular(listOf(aluno1, aluno2))
    formacaoKotlinBasico.matricular(listOf(aluno1, aluno3)) // Carlos será matriculado

    // Imprimir informações da formação após matrícula
    println(formacaoKotlinBasico)
}
