# Juego del Ahorcado

**Laboratorio 4 de Programación II**

### Integrantes
- Ashly Martinez - 22451011
- Rodrigo Hernandez - 22611163
- Angili Palacios - 62611135

### 1. Reglas de validación

Para el funcionamiento del juego se establecieron las siguientes reglas:

- No se diferencian mayúsculas y minúsculas; todas las letras se convierten a mayúsculas.
- Se ignoran las tildes y la diéresis.
- La Ñ se conserva y se considera diferente de la N.
- Se permiten únicamente letras de la A a la Z y la Ñ.
- Las palabras deben tener entre 3 y 15 letras, sin espacios, números ni símbolos.
- Cada intento debe contener una sola letra.
- No se permiten letras repetidas y estas no descuentan intentos.

### 2. Diagrama de clases
classDiagram
direction TB

namespace Modelo {
    class JuegoAhorcado {
        <<interface>>
        +establecerPalabraSecreta(String)
        +ingresarLetra(String) ResultadoLetra
        +reiniciar()
        +getPalabraMostrada()
        +getIntentosRestantes()
        +getEstado()
        +getModalidad()
    }

    class JuegoBase {
        <<abstract>>
        #palabraSecreta String
        #palabraMostrada char[]
        #intentosRestantes int
        #letrasAcertadas List
        #letrasErradas List
        #etapa EtapaAhorcado
        #estado EstadoPartida
        +establecerPalabraSecreta(String)
        +ingresarLetra(String) ResultadoLetra
        +reiniciar()
        #revelarLetra(char, int)
        #haGanado(int) boolean
        +haPerdido() boolean
        #elegirPalabra()* String
        +getModalidad()* Modalidad
    }

    class JuegoPalabraFija {
        -palabraConfigurada String
        +cambiarPalabra(String)
        #elegirPalabra() String
        +getModalidad() Modalidad
    }

    class JuegoAlAzar {
        -administrador AdministradorPalabras
        #elegirPalabra() String
        +getModalidad() Modalidad
    }

    class AdministradorPalabras {
        -palabras ArrayList
        +agregar(String)
        +obtenerAleatoria() String
        -existe(String, int) boolean
        +cantidad() int
        +getPalabras() ArrayList
    }

    class UtilTexto {
        <<utility>>
        +normalizar(String) String
        +esLetra(char) boolean
    }

    class EstadoPartida {
        <<enumeration>>
        EN_CURSO
        GANADA
        PERDIDA
    }

    class ResultadoLetra {
        <<enumeration>>
        ACIERTO
        ERROR
    }

    class Modalidad {
        <<enumeration>>
        PALABRA_FIJA
        AL_AZAR
    }

    class EtapaAhorcado {
        <<enumeration>>
        VACIO
        CABEZA
        TRONCO
        BRAZO_IZQ
        BRAZO_DER
        PIERNA_IZQ
        PIERNA_DER
    }
}

namespace Excepciones {
    class AhorcadoException {
        <<exception>>
    }
    class LetraInvalidaException
    class LetraRepetidaException
    class PartidaTerminadaException
    class PalabraInvalidaException
    class PalabraDuplicadaException
}

namespace Interfaz {
    class Lab4PrograII {
        +main(String[] args)
    }

    class VentanaPrincipal {
        -tarjetas CardLayout
        -contenedor JPanel
        -administrador AdministradorPalabras
        -sonido GestorSonido
    }

    class PanelMenu {
        +PanelMenu(Runnable, Runnable, Runnable, Runnable)
    }

    class PanelJuego {
        -juego JuegoAhorcado
        -sonido GestorSonido
        -dibujo PanelDibujo
        +PanelJuego(JuegoAhorcado, GestorSonido, Runnable)
    }

    class PanelDibujo {
        -etapa EtapaAhorcado
        +setEtapa(EtapaAhorcado)
        +paintComponent(Graphics)
    }

    class PanelAdministracion {
        -administrador AdministradorPalabras
        -sonido GestorSonido
        +agregarPalabra()
        +actualizarLista()
    }

    class GestorSonido {
        -silenciado boolean
        -disponible boolean
        +reproducir(EfectoSonido)
        +setSilenciado(boolean)
        +isSilenciado() boolean
    }

    class EfectoSonido {
        <<enumeration>>
        ACIERTO
        ERROR
        ALERTA
        VICTORIA
        DERROTA
    }
}

JuegoBase ..|> JuegoAhorcado
JuegoPalabraFija --|> JuegoBase
JuegoAlAzar --|> JuegoBase
JuegoAlAzar --> AdministradorPalabras
JuegoBase ..> UtilTexto
AdministradorPalabras ..> UtilTexto

JuegoBase --> EstadoPartida
JuegoBase --> EtapaAhorcado
JuegoBase ..> ResultadoLetra
JuegoBase ..> Modalidad

LetraInvalidaException --|> AhorcadoException
LetraRepetidaException --|> AhorcadoException
PartidaTerminadaException --|> AhorcadoException
PalabraInvalidaException --|> AhorcadoException
PalabraDuplicadaException --|> AhorcadoException

Lab4PrograII --> VentanaPrincipal
VentanaPrincipal --> PanelMenu
VentanaPrincipal --> PanelJuego
VentanaPrincipal --> PanelAdministracion
VentanaPrincipal --> AdministradorPalabras
VentanaPrincipal --> GestorSonido

PanelJuego --> JuegoAhorcado
PanelJuego --> PanelDibujo
PanelJuego --> GestorSonido
PanelJuego --> EfectoSonido
PanelJuego --> EstadoPartida
PanelAdministracion --> AdministradorPalabras
PanelAdministracion --> GestorSonido
PanelAdministracion ..> PalabraInvalidaException
PanelAdministracion ..> PalabraDuplicadaException
PanelDibujo --> EtapaAhorcado
GestorSonido --> EfectoSonido

### 3. Uso de Serializable

Se implementa `Serializable` en las clases del modelo que lo requieren. Esta es una interfaz marcadora, es decir, no contiene métodos que deban implementarse, sino que indica que los objetos pueden ser serializados.

Se utiliza para cumplir con el requisito del trabajo, aunque no se guardan partidas en archivos. Por eso no se utilizan archivos `.dat`, `.ser` ni flujos de serialización.

Las clases que contienen referencias a otros objetos serializables también deben cumplir con este requisito.

---

**Programación II — Juego del Ahorcado**
