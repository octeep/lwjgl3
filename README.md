## LWJGL 3 for Minecraft on OpenBSD 

This repository is an attempt to make Minecraft >= 1.13 work on OpenBSD.   
As of this moment, Minecraft 1.16.5 has been tested to work on my T450s OpenBSD 6.8 -stable.  

## Credits

Huge thanks to kmosiejczuk for his fork of LWJGL 3. I merely fixed some platform issues of his port,    
but he did the majority of the work.   

## Getting Minecraft >= 1.13 to work

The following instructions are what I did in order to get 1.16.5 to work on OpenBSD 6.8 -stable. Some instructions may be
unnecessary, redundant, or incompatible with other Minecraft versions.

Firstly you would need to get the `dyncall`, `openal` and `glfw >= 3.3` ports installed. `dyncall` and `openal` can be installed directly via `pkg_add`,
however `glfw` is still in version 3.2 in OpenBSD's port tree. You would need to compile `glfw 3.3`, the port thereof can be fetched from
`https://marc.info/?l=openbsd-ports&m=158610418115958&w=2` (Credits to Thomas de Grivel for making the port).

Afterwards, you would need to compile LWJGL 3. This process requires two commands.   

`ant -Dos.name=OpenBSD -Dplatform=openbsd`

This generates java templates and compiles them along with the native libraries. Then execute   

`LWJGL_BUILD_OFFLINE=true ant -Dos.name=OpenBSD -Dplatform=openbsd release`    

This would package the compiled resources into jar files and .so libraries. `LWJGL_BUILD_OFFLINE` is set to `true`    
to prevent it from fetching windows, macos stuff that would result in HTTP 404 errors, halting the entire compilation process.    
Note that javadocs are not generated. This is to prevent some weird errors and long compilation time.     

At this point, you would have the required library to run Minecraft. In order to do so, I installed `games/multimc` via `pkg_add`.   
`games/minecraft` was not used because the legacy launcher used cannot parse version infos from Minecraft 1.14.2 onwards.    
In order to get MultiMC to launch Minecraft with the libraries that we have compiled, we would a wrapper script for Java that modifies libraries path location.    
Fetch the script from `https://gist.github.com/octeep/8ffa2fdd02f0ac6ebbe03ca0c393ea4d`, modify the ROOT variable accordingly.    
Create a new instance in MultiMC, go to instance settings, modify `Java installation` path to the script's path.     
When you click `Test` MultiMC would say it does not recognize the script as a valid Java binary, but launching the instance would still work.
Afterwards, move the .so binaries from bin/libs/openbsd to bin/libs.

Now enjoy Minecraft >= 1.13 on your OpenBSD machine :D        

Note: The LWJGL version of this repo is `3.2.4 SNAPSHOT`. Minecraft does not use this version of LWJGL, but since LWJGL 3.x is mostly compatible, it should
also work for most >= 1.13 versions.
