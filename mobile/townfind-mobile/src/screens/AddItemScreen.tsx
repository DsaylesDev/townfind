import React, { useState } from "react";
import { View, Text, TextInput, Button, StyleSheet, Alert } from "react-native";
import { addItem } from "@/src/api/client";

export default function AddItemScreen() {
  const [name, setName] = useState("");
  const [brand, setBrand] = useState("");
  const [sizeText, setSizeText] = useState("");

  const submit = async () => {
    if (!name.trim()) {
      Alert.alert("Error", "Item name is required.");
      return;
    }

    try {
      await addItem({ name, brand, sizeText });
      Alert.alert("Success", "Item added!");
      setName("");
      setBrand("");
      setSizeText("");
    } catch (err) {
      console.error(err);
      Alert.alert("Error", "Failed to add item.");
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Add New Item</Text>

      <TextInput
        style={styles.input}
        placeholder="Item Name"
        value={name}
        onChangeText={setName}
      />

      <TextInput
        style={styles.input}
        placeholder="Brand"
        value={brand}
        onChangeText={setBrand}
      />

      <TextInput
        style={styles.input}
        placeholder="Size (e.g., 1 lb)"
        value={sizeText}
        onChangeText={setSizeText}
      />

      <Button title="Save Item" onPress={submit} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    paddingTop: 40,
  },
  title: {
    fontSize: 22,
    fontWeight: "bold",
    marginBottom: 15,
  },
  input: {
    borderWidth: 1,
    borderRadius: 10,
    padding: 10,
    marginBottom: 12,
    backgroundColor: "#fff",
  },
});
