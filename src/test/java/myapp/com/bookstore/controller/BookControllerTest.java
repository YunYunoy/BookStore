package myapp.com.bookstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import myapp.com.bookstore.model.BookDTO;
import myapp.com.bookstore.security.config.SecurityConfig;
import myapp.com.bookstore.services.BookService;
import myapp.com.bookstore.services.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;
    BookServiceImpl bookServiceImpl;


    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<BookDTO> bookArgumentCaptor;

    @BeforeEach
    void setUp() {
        bookServiceImpl = new BookServiceImpl();
    }

    @Test
    @WithMockUser(authorities = "USER")
    void listBooks() throws Exception {
        given(bookService.listBooks())
                .willReturn(bookServiceImpl.listBooks());

        mockMvc.perform(get(BookController.BOOK_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @WithMockUser(authorities = "USER")
    void listBooksPageable() throws Exception {
        given(bookService.listBooksPageable(any(), any() , any(), any()))
                .willReturn(bookServiceImpl.listBooksPageable(null, null, null, null));

        mockMvc.perform(get(BookController.BOOK_PATH+"/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(authorities = "USER")
    void getBookById() throws Exception {
        BookDTO testBookDto = bookServiceImpl.listBooks().get(0);

        given(bookService.getBookById(testBookDto.getId()))
                .willReturn(Optional.of(testBookDto));

        mockMvc.perform(get(BookController.BOOK_PATH, testBookDto.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void getBookByIdNotFound() throws Exception {
        given(bookService.getBookById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(BookController.BOOK_PATH_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void createBook() throws Exception {
        BookDTO book = bookServiceImpl.listBooks().get(0);
        book.setVersion(null);
        book.setId(null);

        given(bookService.saveNewBook(any(BookDTO.class))).willReturn(bookServiceImpl.listBooks().get(1));

        mockMvc.perform(post(BookController.BOOK_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
    }


    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void updateById() throws Exception {
        BookDTO book = bookServiceImpl.listBooks().get(0);

        mockMvc.perform(put(BookController.BOOK_PATH_ID, book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isNoContent());

        verify(bookService).updateBookById(any(UUID.class), any(BookDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deleteById() throws Exception {
        BookDTO book = bookServiceImpl.listBooks().get(0);

        mockMvc.perform(delete(BookController.BOOK_PATH_ID, book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(bookService).deleteBookById(uuidArgumentCaptor.capture());
        assertThat(book.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void updateBookPatchById() throws Exception {
        BookDTO book = bookServiceImpl.listBooks().get(0);

        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("title", "new title");

        mockMvc.perform(patch(BookController.BOOK_PATH_ID, book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(bookMap)))
                .andExpect(status().isNoContent());

        verify(bookService).patchBookById(uuidArgumentCaptor.capture(), bookArgumentCaptor.capture());

        assertThat(book.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(bookMap.get("title")).isEqualTo(bookArgumentCaptor.getValue().getTitle());
    }
}