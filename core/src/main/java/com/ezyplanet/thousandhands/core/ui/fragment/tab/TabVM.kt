package com.ezyplanet.thousandhands.core.ui.fragment.tab



import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.ui.base.MvvmNav
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager
import javax.inject.Inject

class TabVM @Inject constructor(
        schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<MvvmNav>(schedulerProvider, connectionManager) {




}