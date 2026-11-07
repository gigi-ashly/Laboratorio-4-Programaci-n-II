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
```mermaid
classDiagram
    direction TB

    class Lab4PrograII

    class JuegoAhorcado {
        <<interface>>
        +ingresarLetra(char letra) ResultadoLetra
        +reiniciar()
        +getModalidad() Modalidad
    }

    class JuegoBase {
        <<abstract>>
        #palabraSecreta String
        #palabraMostrada String
        #intentosRestantes int
        #MAX_INTENTOS int
        #letrasAcertadas Set
        #letrasErradas Set
        #etapa EtapaAhorcado
        #estado EstadoPartida
        +ingresarLetra(char letra) ResultadoLetra
        +reiniciar()
        +revelarLetra(int indice, char letra)
        +haGanado() boolean
        +haPerdido() boolean
        +elegirPalabra()*
        +getModalidad()*
    }

    class JuegoPalabraFija {
        -palabraConfigurada String
        +cambiarPalabra(String palabra)
        +elegirPalabra()
        +getModalidad() Modalidad
    }

    class JuegoAlAzar {
        -administrador AdministradorPalabras
        +elegirPalabra()
        +getModalidad() Modalidad
    }

    class AdministradorPalabras {
        -palabras ArrayList
        +agregar(String palabra)
        +obtenerAleatoria() String
        +existe(String palabra, int indice) boolean
        +cantidad() int
        +getPalabras() ArrayList
    }

    class UtilTexto {
        <<utility>>
        +normalizar(String texto) String
        +esLetra(char letra) boolean
    }

    class EstadoPartida {
        <<enumeration>>
    }

    class ResultadoLetra {
        <<enumeration>>
    }

    class Modalidad {
        <<enumeration>>
    }

    class EtapaAhorcado {
        <<enumeration>>
    }

    class AhorcadoException {
        <<exception>>
    }

    class LetraInvalidaException
    class LetraRepetidaException
    class PartidaTerminadaException
    class PalabraInvalidaException
    class PalabraDuplicadaException

    class VentanaPrincipal {
        -tarjetas CardLayout
        -contenedor JPanel
        -administrador AdministradorPalabras
        -sonido GestorSonido
        -panelMenu PanelMenu
        -panelAdministracion PanelAdministracion
    }

    class PanelMenu

    class PanelJuego {
        -juego JuegoAhorcado
        -sonido GestorSonido
        -dibujo PanelDibujo
    }

    class PanelDibujo {
        -etapa EtapaAhorcado
        +setEtapa(EtapaAhorcado)
        +paintComponent(Graphics)
    }

    class PanelAdministracion {
        -administrador AdministradorPalabras
        -sonido GestorSonido
    }

    class GestorSonido {
        +reproducir(EfectoSonido)
    }

    class EfectoSonido {
        <<enumeration>>
    }

    JuegoAhorcado <|.. JuegoBase
    JuegoBase <|-- JuegoPalabraFija
    JuegoBase <|-- JuegoAlAzar
    JuegoAlAzar --> AdministradorPalabras
    JuegoBase ..> UtilTexto
    AdministradorPalabras ..> UtilTexto

    JuegoBase --> EstadoPartida
    JuegoBase --> ResultadoLetra
    JuegoBase --> Modalidad
    JuegoBase --> EtapaAhorcado

    AhorcadoException <|-- LetraInvalidaException
    AhorcadoException <|-- LetraRepetidaException
    AhorcadoException <|-- PartidaTerminadaException
    AhorcadoException <|-- PalabraInvalidaException
    AhorcadoException <|-- PalabraDuplicadaException

    Lab4PrograII --> VentanaPrincipal
    VentanaPrincipal --> PanelMenu
    VentanaPrincipal --> PanelJuego
    VentanaPrincipal --> PanelAdministracion
    VentanaPrincipal --> AdministradorPalabras
    VentanaPrincipal --> GestorSonido

    PanelJuego --> JuegoAhorcado
    PanelJuego --> PanelDibujo
    PanelJuego --> GestorSonido
    PanelDibujo --> EtapaAhorcado
    PanelAdministracion --> AdministradorPalabras
    PanelAdministracion --> GestorSonido

    GestorSonido --> EfectoSonido
```
### 3. Uso de Serializable

Se implementa `Serializable` en las clases del modelo que lo requieren. Esta es una interfaz marcadora, es decir, no contiene métodos que deban implementarse, sino que indica que los objetos pueden ser serializados.

Se utiliza para cumplir con el requisito del trabajo, aunque no se guardan partidas en archivos. Por eso no se utilizan archivos `.dat`, `.ser` ni flujos de serialización.

Las clases que contienen referencias a otros objetos serializables también deben cumplir con este requisito.

---

**Programación II — Juego del Ahorcado**
