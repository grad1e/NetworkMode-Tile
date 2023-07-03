package dev.daryl.networkmodetile

import android.content.Intent
import android.service.quicksettings.TileService
import android.widget.Toast

class AppTileService : TileService() {

    override fun onClick() {
        super.onClick()
        openNetworkSettings()
    }

    private fun openNetworkSettings() {
        try {
            Intent().apply {
                action = "android.settings.DATA_ROAMING_SETTINGS"
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }.run {
                startActivityAndCollapse(this)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

}