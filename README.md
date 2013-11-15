# LiBRE! Android aplikacija

Ovde se nalazi izvorni kod LiBRE! Android aplikacije, koja je u razvoju.

Licencirano pod GPL v3 licencom.

## Uputstvo za razvoj

### Razvojne komponente

* [Eclipse](http://www.eclipse.org/) i [EGit](http://www.eclipse.org/egit/)
* [Android SDK](http://developer.android.com/sdk)
* [ActionBarSherlock](http://actionbarsherlock.com)

### Razvoj pomoću komandne linije

* Ažuriranje:
  * `$ android update project --target=android-18 --path . --library=/putanja/do/abs/biblioteke`
  * `--library` opcija zahteva putanju u odnosu na putanju projekta (npr. ako je projekat u `/data/libre`, a ABS u `/data/abs` treba zadati putanju `../abs/`)
* Kompajliranje: `$ ant debug`
* Instaliranje: `$ adb -d install -r bin/MainActivity-debug.apk`
