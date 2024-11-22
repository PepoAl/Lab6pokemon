import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.lab6pokemon.PokemonDetailScreen
import com.uvg.lab6pokemon.PokemonListScreen

@Composable
fun PokemonApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pokemonList") {
        composable("pokemonList") { PokemonListScreen(navController) }
        composable("pokemonDetail/{pokemonId}/{pokemonName}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")
            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
            if (pokemonId != null && pokemonName != null) {
                PokemonDetailScreen(pokemonId, pokemonName)
            }
        }
    }
}
