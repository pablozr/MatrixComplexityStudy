import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

caminho_csv = r'C:\Users\PABLO\Desktop\trabalhoEDD-main\desempenho_matrizes.csv'
df = pd.read_csv(caminho_csv, delimiter=";")

df['TempoMedio'] = df['TempoMedio'].str.replace(',', '.')

df['TempoMedio'] = df['TempoMedio'].astype(float)

print("Dados carregados:")
print(df.head())

sns.set(style="whitegrid", palette="deep", font_scale=1.1)

metodos = df['Metodo'].unique()

for metodo in metodos:
    dados_metodo = df[df['Metodo'] == metodo]
    
    plt.figure(figsize=(12, 7))
    ax = sns.lineplot(data=dados_metodo, x='Tamanho', y='TempoMedio', hue='Classe', style='Classe', 
                      markers=True, dashes=False, linewidth=2.5, markersize=10)
    
    plt.title(f'Desempenho do Método: {metodo}', fontsize=18, pad=20)
    plt.xlabel('Tamanho da Matriz', fontsize=14, labelpad=10)
    plt.ylabel('Tempo Médio (segundos)', fontsize=14, labelpad=10)
    
    plt.legend(title='Classe', bbox_to_anchor=(1.05, 1), loc='upper left', fontsize=12, title_fontsize=14)
    
    plt.grid(True, which='both', linestyle='--', linewidth=0.5)
    
    for tamanho in [10, 100, 1000, 10000]:
        dados_ponto = dados_metodo[dados_metodo['Tamanho'] == tamanho]
        for _, row in dados_ponto.iterrows():
            ax.text(row['Tamanho'], row['TempoMedio'], f"{row['TempoMedio']:.6f}", 
                    fontsize=9, color='black', ha='center', va='bottom')
    
    plt.yscale('log')
    plt.ylabel('Tempo Médio (segundos) - Escala Logarítmica', fontsize=14, labelpad=10)
    
    plt.tight_layout()
    
    nome_arquivo = f'grafico_{metodo}.png'
    plt.savefig(nome_arquivo, dpi=300, bbox_inches='tight')
    
    plt.show()