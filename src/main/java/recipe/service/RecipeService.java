package recipe.service;

import recipe.entity.Recipe;
import recipe.entity.RecipeSteps;
import recipe.entity.User;

import java.util.List;

public interface RecipeService {

	// 레시피 생성
	Recipe saveRecipe(Recipe newRecipe, User user);

	// 레시피 수정
	Recipe updateRecipe(Long recipeId, Recipe updatedRecipe, User user, List<RecipeSteps> steps);

	// 레시피 논리 삭제
	void softDeleteRecipe(Long recipeId, User user);

	// 일반 사용자의 레시피 조회
	List<Recipe> getAllRecipes();

	// 관리자 사용자의 모든 레시피 조회
	List<Recipe> getAllRecipesForAdmin();
}
