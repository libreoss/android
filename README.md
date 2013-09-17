# LiBRE! Android aplikacija

Ovo je repozitorijum sa izvornim kodom LiBRE! Android aplikacije. Aplikacija je u razvoju.

## Uputstvo za razvoj

### Razvojne komponente

* Eclipse + EGit
* Android SDK
* ActionBarSherlock
 * http://actionbarsherlock.com/faq.html
 * http://actionbarsherlock.com/usage.html

### Razvoj pomoću komandne linije

* Ažuriranje projekta:
  * `android update project --target=android-18 --path . --library=/putanja/do/abs/biblioteke`
  * `--library` opcija zahteva putanju u odnosu na putanju projekta (npr. ako je projekat u `/data/libre`, a ABS u `/data/abs` treba zadati putanju `../abs/`)
* Kompajliranje projekta: `ant debug`
* Instalacija na uređaj ili simulator: `adb -d install -r bin/MainActivity-debug.apk`
