# menu Package

This code presents  a ``menu`` package for creating menu-driven interfaces
(mdi) within the terminal using an object-oriented approach.

Each menu item (that is, a numbered item in the menu) is represented by a ``MenuItem`` object that encapsulates both the menu item's text and the method to be called when it is selected. 

The ``Menu`` class aggregates and manages many ``MenuItem`` objects, for example,
formatting the menu and facilitating selection of a menu item.

This is the approach required for this course.

HINT: Use ``ant javadoc`` in this directory to build complete reference documentation for the ``menu`` package. You'll find it under docs/api/index.html.

https://github.com/prof-rice/cse1325-prof/10-java-user-interfaces/code_from_slides/ provides many constrasting implementations of menus for the more adventurous student: A class C-like hard-coded menu with a switch, the use of the attomenu package, both a classic command-line option and more modern picocli package command-line implementation, and of course a Swing graphical user interface version.

