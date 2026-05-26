# Doodie – Java Swing E-commerce Application

Doodie is a desktop e-commerce app built with **Java 17** and **Swing**. It provides user authentication, product browsing, cart and checkout flow, profile management, and admin tooling to manage items and review orders.

## Features

- **Authentication**
  - User sign-up and sign-in interfaces.
  - Separate admin sign-in flow.
- **Shopping Experience**
  - Home screen with product cards.
  - Add items to cart, update cart contents, and checkout.
  - User profile view.
- **Admin Experience**
  - Add new products.
  - Edit existing products.
  - Check incoming orders.
- **Custom Data Structures**
  - Includes custom implementations of `ArrayList`, `HashMap`, `Queue`, `Stack`, `Tree`, and deque/list variants in `src/datastructures`.
- **Look & Feel**
  - Uses FlatLaf (Darcula theme) for a modern Swing UI.
  - Bundled fonts and icons.

## Project Structure

```text
src/
  admin/           # Admin panels (add/edit items, check orders)
  authorize/       # Sign-in/sign-up/admin authentication UI
  datastructures/  # Custom data structure implementations
  entity/          # Core models (User, Item, Cart, Order)
  fonts/           # TTF font assets
  images/          # UI icons/images
  main/            # App entry point (MainWindow)
  managers/        # Managers for users, items, orders, fonts
  record/          # Serialized data files (*.ser)
```

## Tech Stack

- **Language:** Java 17
- **UI:** Swing + FlatLaf
- **Build:** Apache Ant (NetBeans-style project)
- **Persistence:** Serialized records (`.ser`) under `src/record`

## Requirements

- JDK 17+
- Apache Ant (if building from source)

## Running the App

### Option 1: Run prebuilt JAR (quickest)

From the repository root:

```bash
java -cp "dist/EcommerceApp.jar:dist/lib/*" main.MainWindow
```

> On Windows PowerShell / CMD, use `;` instead of `:` in the classpath.

### Option 2: Build from source with Ant

```bash
ant clean jar
java -cp "dist/EcommerceApp.jar:dist/lib/*" main.MainWindow
```

## Development Notes

- Main entry point: `src/main/MainWindow.java`
- Main customer UI: `src/view/Home.java`
- Project metadata and build settings:
  - `build.xml`
  - `nbproject/project.properties`

## Known Considerations

- The NetBeans property file includes machine-specific classpath references; if you build from source on a different machine, you may need to update dependency paths in `nbproject/project.properties`.
- Application data is currently persisted through serialized files in `src/record/*.ser`.

## Author

Originally authored by **ShaheerZK**.
