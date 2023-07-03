package dev.daryl.networkmodetile

import android.app.StatusBarManager
import android.content.ComponentName
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.res.stringResource


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addTileToQS()
    }


    private fun addTileToQS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val statusBarManager = getSystemService(StatusBarManager::class.java)
            statusBarManager.requestAddTileService(
                ComponentName(
                    this,
                    AppTileService::class.java
                ),
                getString(R.string.tile_name),
                Icon.createWithResource(this, R.drawable.ic_tile_network),
                mainExecutor
            ) {
                when (it) {
                    StatusBarManager.TILE_ADD_REQUEST_RESULT_TILE_ALREADY_ADDED ->
                        Toast.makeText(this, "Tile already added", Toast.LENGTH_SHORT).show()

                    StatusBarManager.TILE_ADD_REQUEST_RESULT_TILE_NOT_ADDED ->
                        Toast.makeText(this, "Tile not added", Toast.LENGTH_SHORT).show()

                    StatusBarManager.TILE_ADD_REQUEST_RESULT_TILE_ADDED ->
                        Toast.makeText(this, "Tile added", Toast.LENGTH_SHORT).show()

                    3 -> Toast.makeText(this, "Dialog dismissed", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        } else {
            Log.i(TAG, "addTileToQS: not supported by OS")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
