# Kotest GraalVM Reproducer

This repository demonstrates that Kotest is currently not usable with GraalVM's tracing agent
due to its usage of `Classgraph.scan()`.

More information about this problem and possible mitigations can be found [here](https://github.com/SoftInstigate/classgraph-on-graalvm).

## Steps

# Install and configure GraalVM

```
sdk env install
```

# Run nativeTest

```
./gradlew -Pagent nativeTest
```

This is the relevant output

```
The culprit object has been instantiated with the following trace:
        at kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.<init>(JvmBuiltInClassDescriptorFactory.kt:19)
        at kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsPackageFragmentProvider.<init>(JvmBuiltInsPackageFragmentProvider.kt:48)
        at kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava$Companion.createModuleData(DeserializationComponentsForJava.kt:133)
        at kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData$Companion.create(RuntimeModuleData.kt:32)
        at kotlin.reflect.jvm.internal.ModuleByClassLoaderKt.getOrCreateModule(moduleByClassLoader.kt:58)
        at kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Data$moduleData$2.invoke(KDeclarationContainerImpl.kt:36)
        at kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Data$moduleData$2.invoke(KDeclarationContainerImpl.kt:35)
        at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(ReflectProperties.java:93)
        at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:32)
        at kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Data.getModuleData(KDeclarationContainerImpl.kt:35)
        at kotlin.reflect.jvm.internal.KClassImpl$Data$descriptor$2.invoke(KClassImpl.kt:50)
        at kotlin.reflect.jvm.internal.KClassImpl$Data$descriptor$2.invoke(KClassImpl.kt:48)
        at kotlin.reflect.jvm.internal.ReflectProperties$LazySoftVal.invoke(ReflectProperties.java:93)
        at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:32)
        at kotlin.reflect.jvm.internal.KClassImpl$Data.getDescriptor(KClassImpl.kt:48)
        at kotlin.reflect.jvm.internal.KClassImpl.getDescriptor(KClassImpl.kt:182)
        at kotlin.reflect.jvm.internal.KClassImpl.getVisibility(KClassImpl.kt:262)
        at io.kotest.framework.discovery.DiscoveryFilter$ClassModifierDiscoveryFilter.test(DiscoveryFilter.kt:35)
        at io.kotest.framework.discovery.Discovery$filterFn$1.invoke(Discovery.kt:61)
        at io.kotest.framework.discovery.Discovery$filterFn$1.invoke(Discovery.kt:60)
        at kotlin.sequences.FilteringSequence$iterator$1.calcNext(Sequences.kt:171)
        at kotlin.sequences.FilteringSequence$iterator$1.hasNext(Sequences.kt:194)
        at kotlin.sequences.FilteringSequence$iterator$1.calcNext(Sequences.kt:169)
        at kotlin.sequences.FilteringSequence$iterator$1.hasNext(Sequences.kt:194)
        at kotlin.sequences.FilteringSequence$iterator$1.calcNext(Sequences.kt:169)
        at kotlin.sequences.FilteringSequence$iterator$1.hasNext(Sequences.kt:194)
        at kotlin.sequences.SequencesKt___SequencesKt.toList(_Sequences.kt:809)
        at io.kotest.framework.discovery.Discovery.doDiscovery-IoAF18A(Discovery.kt:109)
        at io.kotest.framework.discovery.Discovery.discover(Discovery.kt:73)
        at io.kotest.runner.junit.platform.KotestJunitPlatformTestEngine.discover(KotestJunitPlatformTestEngine.kt:122)
        at io.kotest.runner.junit.platform.KotestJunitPlatformTestEngine.discover(KotestJunitPlatformTestEngine.kt:39)
```
