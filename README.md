# 📊 **Sparse Matrix Methods** 📊

This project was developed as part of an academic assignment, with the goal of implementing specific methods for handling **sparse matrices** using two distinct approaches: the **static approach** and the **linked list approach**.

---

## 🎯 **Objectives**

The main goal of this project was to implement efficient methods for operations on sparse matrices, considering two different storage approaches:

- **Static Approach**: Uses a conventional matrix where non-zero elements are stored in a predefined structure.
- **Linked List Approach**: Uses linked lists to store only non-zero elements, optimizing memory usage.

In addition to the method implementations, computational experiments were conducted to evaluate the **performance** of each approach, analyzing the **execution time** of specific operations on matrices of varying sizes.

---

## 🧪 **Computational Experiments**

After implementing the methods, a series of experiments were conducted to measure the **execution time** of each approach under different scenarios. 

### Methods Tested:
- **Multiplication**
- **Addition**
- **Transpose**

Each of these methods was executed **10 times** for each matrix size, and the **average execution time** was calculated. The matrix sizes varied from **10x10** up to **1000** x **1000**.

### Computer Specifications:
- **Processor**: AMD Ryzen 5 5600G with Radeon Graphics (3.90 GHz)
- **RAM**: 32.0 GB (usable: 23.9 GB)
- **System Type**: 64-bit operating system, x64 processor
- **GPU**: RTX 3060 12GB

The goal of these experiments was to analyze the **computational complexity** of the operations and how the type of approach impacts **performance** and **execution time**. Based on these experiments, we were able to compare the efficiency of the two approaches and better understand how matrix characteristics (such as size and the number of non-zero elements) affect the performance of the implemented methods.

---

## ⚙️ **How to Use**

To run the experiments and use the implementations, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/HenriqRocha/trabalhoEDD.git
2. **Install dependencies**
   ```bash
   pip install -r requirements.txt
3. **Run the tests**
   ```bash
   python main.py
---

## 📊 **Results**
The results of the experiments are available in the repository, including graphs and analyses of the impact of the static and linked list approaches on the execution time of operations. These data can be used to understand the viability of each approach depending on the size and characteristics of the matrices.
---

## 📝 **Conclusion**
This project offers a comparison between two approaches for handling sparse matrices, providing a deeper analysis of how different implementation strategies impact the performance of computational operations in large-scale problems. The results offer valuable insights for choosing the most efficient approach depending on the context and memory requirements.
---
## 📚 **References**
Sparse Matrix Methods - Theory and Application: https://www.geeksforgeeks.org/sparse-matrix-representation/ and https://www.geeksforgeeks.org/sparse-matrix-representations-using-list-lists-dictionary-keys/
Python Documentation: https://docs.python.org/3/
---
🔧 Authors: Pablo Farina, Henrique Rocha, Matheus Marques
🎓 Institution: UNIRIO( Universidade Federal do Estado do Rio de Janeiro)

