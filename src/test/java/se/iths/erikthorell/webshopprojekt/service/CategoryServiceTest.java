package se.iths.erikthorell.webshopprojekt.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.erikthorell.webshopprojekt.model.Category;
import se.iths.erikthorell.webshopprojekt.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldReturnAllCategories() {
        Category category1 = new Category();
        Category category2 = new Category();

        when(categoryRepository.findAll())
                .thenReturn(List.of(category1, category2));

        List<Category> result = categoryService.findAll();

        assertThat(result).hasSize(2);
        verify(categoryRepository).findAll();
    }

    @Test
    void shouldReturnCategoryWhenIdExists() {
        Category category = new Category();
        category.setId(1L);

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        Category result = categoryService.findById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        verify(categoryRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAudiobookNotFound() {
        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.findById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");
    }
}