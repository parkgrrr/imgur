package net.parkerstevens.imgurdemo.injection.component

import dagger.Subcomponent
import net.parkerstevens.imgurdemo.injection.PerFragment
import net.parkerstevens.imgurdemo.injection.module.FragmentModule

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent