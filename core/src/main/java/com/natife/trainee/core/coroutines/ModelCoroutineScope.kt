package com.fractaldev.core.coroutines

import com.natife.trainee.core.coroutines.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ModelCoroutineScope(dispatchers: AppDispatchers) : CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatchers.default
}