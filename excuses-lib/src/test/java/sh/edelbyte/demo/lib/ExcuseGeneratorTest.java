package sh.edelbyte.demo.lib;

import org.junit.jupiter.api.Test;
import sh.edelbyte.demo.domain.Excuse;
import sh.edelbyte.demo.domain.Excuse.Category;

import static org.assertj.core.api.Assertions.assertThat;

class ExcuseGeneratorTest {
    
    @Test
    void testGetRandomSlowBuildExcuse() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        Excuse excuse = generator.getRandomSlowBuildExcuse();
        
        assertThat(excuse).isNotNull();
        assertThat(excuse.getCategory()).isEqualTo(Category.SLOW_BUILD);
        assertThat(excuse.getDescription()).isNotEmpty();
    }
    
    @Test
    void testGetRandomFailedBuildExcuse() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        Excuse excuse = generator.getRandomFailedBuildExcuse();
        
        assertThat(excuse).isNotNull();
        assertThat(excuse.getCategory()).isEqualTo(Category.FAILED_BUILD);
        assertThat(excuse.getDescription()).isNotEmpty();
    }
    
    @Test
    void testGetRandomExcuse() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        Excuse excuse = generator.getRandomExcuse();
        
        assertThat(excuse).isNotNull();
        assertThat(excuse.getCategory()).isIn(Category.SLOW_BUILD, Category.FAILED_BUILD);
        assertThat(excuse.getDescription()).isNotEmpty();
    }
    
    @Test
    void testGetRandomExcuseForCategory() {
        ExcuseGenerator generator = new ExcuseGenerator(42L);
        
        Excuse slowBuild = generator.getRandomExcuseForCategory(Category.SLOW_BUILD);
        assertThat(slowBuild.getCategory()).isEqualTo(Category.SLOW_BUILD);
        
        Excuse failedBuild = generator.getRandomExcuseForCategory(Category.FAILED_BUILD);
        assertThat(failedBuild.getCategory()).isEqualTo(Category.FAILED_BUILD);
    }
    
    @Test
    void testExcuseValueObject() {
        Excuse excuse = new Excuse(Category.SLOW_BUILD, "Test excuse");
        
        assertThat(excuse.getCategory()).isEqualTo(Category.SLOW_BUILD);
        assertThat(excuse.getDescription()).isEqualTo("Test excuse");
    }
}
