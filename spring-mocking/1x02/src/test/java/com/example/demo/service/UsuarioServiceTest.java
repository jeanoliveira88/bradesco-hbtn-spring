package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Usuário Teste");
        usuario.setEmail("teste@example.com");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarUsuarioPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Usuário Teste", resultado.getNome());
        assertEquals("teste@example.com", resultado.getEmail());

        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 2L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(id));

        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario paraSalvar = new Usuario();
        paraSalvar.setNome("Novo Usuário");
        paraSalvar.setEmail("novo@example.com");

        Usuario salvo = new Usuario();
        salvo.setId(10L);
        salvo.setNome(paraSalvar.getNome());
        salvo.setEmail(paraSalvar.getEmail());

        when(usuarioRepository.save(paraSalvar)).thenReturn(salvo);

        Usuario resultado = usuarioService.save(paraSalvar);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("Novo Usuário", resultado.getNome());
        assertEquals("novo@example.com", resultado.getEmail());

        verify(usuarioRepository, times(1)).save(paraSalvar);
    }

}
