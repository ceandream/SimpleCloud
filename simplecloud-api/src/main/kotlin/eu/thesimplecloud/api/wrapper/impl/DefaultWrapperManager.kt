package eu.thesimplecloud.api.wrapper.impl

import eu.thesimplecloud.api.wrapper.IWrapperInfo
import eu.thesimplecloud.api.wrapper.IWrapperManager
import eu.thesimplecloud.api.wrapper.IWritableWrapperInfo
import java.util.*

open class DefaultWrapperManager : IWrapperManager {

    private val wrappers = Collections.synchronizedCollection(ArrayList<IWrapperInfo>())

    override fun updateWrapper(wrapper: IWrapperInfo) {
        if (!this.wrappers.contains(wrapper)) {
            this.wrappers.add(wrapper)
            return
        }
        val cashedWrapper = getWrapperByHost(wrapper.getHost())
        cashedWrapper as IWritableWrapperInfo
        cashedWrapper.setMaxMemory(wrapper.getMaxMemory())
        cashedWrapper.setMaxSimultaneouslyStartingServices(wrapper.getMaxSimultaneouslyStartingServices())
        cashedWrapper.setUsedMemory(wrapper.getUsedMemory())
        cashedWrapper.setAuthenticated(wrapper.isAuthenticated())
        cashedWrapper.setTemplatesReceived(wrapper.hasTemplatesReceived())
    }

    override fun removeWrapper(wrapper: IWrapperInfo) {
        this.wrappers.remove(wrapper)
    }

    override fun getAllWrappers(): Collection<IWrapperInfo> = this.wrappers
}